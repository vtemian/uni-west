package w;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

public class Config {
    private String configFilePath = "/etc/default/w/w.conf";
    private HashMap<String, HashMap<String, String>> config;

    public Config() {
        this.config = new HashMap<>();
    }

    public HashMap<String, String> getFor(String host) {
        // I will assume that the config is correct and contains at least the default one
        HashMap<String, String> hostConfig = config.get(host);
        if (hostConfig == null) {
            hostConfig = config.get("default");
        }
        return (HashMap<String, String>) hostConfig.clone();
    }

    public void loadConfig() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(configFilePath)));
            config = parseConfig(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setConfigFilePath(String configFilePath){
        this.configFilePath = configFilePath;
    }

    public HashMap<String, HashMap<String, String>> parseConfig(String rawConfig) throws JSONException {
        HashMap<String, HashMap<String, String>> config = new HashMap<>();

        JSONObject jConfig = new JSONObject(rawConfig);
        Iterator<?> keys = jConfig.keys();

        while( keys.hasNext() ){
            String host = (String)keys.next();
            JSONObject value = jConfig.getJSONObject(host);

            HashMap<String, String> hostConfig = new HashMap<>();
            Iterator<?> rawHostConfigs = value.keys();
            while( rawHostConfigs.hasNext() ) {
                String configName = (String) rawHostConfigs.next();
                String configValue = value.getString(configName);
                hostConfig.put(configName, configValue);
            }

            config.put(host, hostConfig);
        }
        return config;
    }
}