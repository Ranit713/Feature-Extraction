package main;

import java.io.File;

public class FeatureExtraction {

    private Parser parser;
    private CSVOperations csvOper;

    public FeatureExtraction() {
        parser = new Parser();
        csvOper = new CSVOperations();
    }

    private void run(String verilogFile) {
        parser.run(verilogFile);
        csvOper.write(verilogFile);
    }

    private void allFiles() {
        File folder = new File("../resources/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
            run(file.getName());
    }

    public static void main(String[] args) {
        FeatureExtraction featureExtract = new FeatureExtraction();
        featureExtract.allFiles();
    }
}
