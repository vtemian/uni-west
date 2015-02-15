package circuit.importer.parser;

import java.util.List;

public interface IParser {
    public String getFilePath();
    public void setFilePath(String filePath);

    public String getTokens();
    public void setTokens(String tokens);

    public List<String[]> parse();
}
