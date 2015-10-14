package w;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Processor {
    private Config config;

    public Processor(Config config) {
        this.config = config;
    }

    public String process(HashMap<String, String> request) {
        String host = request.get("Host").split(":")[0];
        HashMap<String, String> configForHost;
        if (host == null)
            configForHost = config.getFor("default");
        else
            configForHost = config.getFor(host);

        if (!request.get("Method").equals("GET"))
            return invalidMethod(request.get("Method"));

        if (!request.get("Version").equals("HTTP/1.1"))
            return invalidMethod(request.get("Version"));

        String resource = configForHost.get("root") + request.get("Resource");

        // check if resource exists
        if (resourceExists(resource))
            return loadStaticResource(resource);

        if (isDirectory(resource)) {
            if (resourceExists(resource + "/index.html")) {
                return loadStaticResource(resource + "/index.html");
            }
            return loadDirectoryIndex(resource, request.get("Resource"));
        }

        return loadStaticResource(configForHost.get("404"));
    }

    public String invalidMethod(String method) {
        return Processor.buildTextResponse("405", "Method not allowed");
    }

    public boolean resourceExists(String resource) {
        File f = new File(resource);
        return f.exists() && !f.isDirectory();
    }

    public boolean isDirectory(String resource) {
        File f = new File(resource);
        return f.exists() && f.isDirectory();
    }

    public static String invalidRequest() {
        return buildTextResponse("400", "Invalid Request");
    }

    public static String buildTextResponse(String statusCode, String content) {
        return Processor.buildResponse(statusCode, content, "text/html");
    }
    public static String buildResponse(String statusCode, String content, String contentType) {
        return  "HTTP/1.1 " + statusCode + " OK\n" +
                "Content-Type: " + contentType + "\n" +
                "Connection: close\n" +
                "Content-Length: " + (content.length() + 1) + "\n\n" +
                content;
    }

    public String loadStaticResource(String resource) {
        try {
            String contentType = Files.probeContentType(Paths.get(resource));
            String content = new String(Files.readAllBytes(Paths.get(resource)));
            return Processor.buildResponse("200", content, contentType);
        } catch (IOException e) {
            return Processor.buildTextResponse("500", "Server Error");
        }
    }

    public String loadTemplate(String template) throws IOException {
        String path = config.getFor("default").get("root") + "/templates/" + template;
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public String loadDirectoryIndex(String resource, String base) {
        base = base.substring(1);
        try {
            String template = loadTemplate("directory_index.html");
            String content = "<ul><li><a href='..'>..</a></li>";
            File directory = new File(resource);

            for (File file: directory.listFiles()) {
                String name = file.getName();
                if (file.isFile()) {
                    content += "<li><a href=" + name +">" + name + "</a></li>";
                } else if (file.isDirectory()) {
                    content += "<li><a href=" + name +"/>" + name + "/</a></li>";
                }
            }
            content += "</ul>";

            template = template.replace("{{ list }}", content);
            template = template.replace("{{ directory }}", base);
            return Processor.buildResponse("200", template, "text/html");
        } catch (IOException e) {
            return Processor.buildTextResponse("500", "Server Error");
        }
    }
}
