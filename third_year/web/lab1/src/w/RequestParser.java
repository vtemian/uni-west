package w;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class RequestParser {
    private static enum METHODS {
        GET, POST, PUT, PURGE, DELETE, OPTIONS, HEAD
    };

    public HashMap<String, String> parseRequest(BufferedReader reader) throws IOException, IllegalArgumentException {
        HashMap<String, String> request = new HashMap<>();

        String line = reader.readLine();
        String[] result = line.split(" ");
        if (result.length != 3)
            throw new IllegalArgumentException();

        request.put("Method", result[0]);
        request.put("Resource", result[1]);
        request.put("Version", result[2]);

        while((line=reader.readLine()) != null) {
            if (line.trim().equals("")) {
                break;
            }

            result = line.split(": ");
            request.put(result[0], result[1]);

        }

        return (HashMap<String, String>) request.clone();
    }
}
