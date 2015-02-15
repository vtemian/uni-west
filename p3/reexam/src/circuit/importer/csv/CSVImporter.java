package circuit.importer.csv;

import circuit.importer.IImporter;
import circuit.importer.parser.CSVParser;

import java.util.List;

public abstract class CSVImporter implements IImporter {
    protected List<String[]> data;

    public CSVImporter(String fileName) {
        CSVParser parser = new CSVParser(fileName, ",");
        data = parser.parse();
    }

}
