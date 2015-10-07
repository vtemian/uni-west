package tramways.application.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser{
    private String filePath;
    private String tokens;

    public Parser(String filePath, String tokens) {
        this.filePath = filePath;
        this.tokens = tokens;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getTokens() {
        return tokens;
    }

    @Override
    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    @Override
    public List<String[]> parse() {
        String line = "";
        BufferedReader bufferedReader = null;
        List<String[]> parsedLines = new ArrayList<String[]>();

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                // use tokens as separator
                String[] row = line.split(tokens);
                parsedLines.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return parsedLines;
    }
}
