package main;

import java.io.File;

public class FeatureExtraction {

    private Parser parser;
    private CSVOperations csvOper;

    public FeatureExtraction() {
        parser = new Parser();
        csvOper = new CSVOperations();
    }

    private void create(String verilogFile) {
        File vfile = new File(verilogFile);
        parser.createGraph(vfile);
        csvOper.write(vfile);
    }

    public static void main(String[] args) {
        FeatureExtraction featureExtract = new FeatureExtraction();
        featureExtract.create(args[0]);
    }
}
