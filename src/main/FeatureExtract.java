package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import graphs.Graph;
import parsers.tokenizer.FileParser;
import queries.Query;

public class FeatureExtract {

    Graph graph;
    Query query;

    public FeatureExtract() {
        graph = Graph.getInstance();
        query = new Query();
    }

    public File openFile(String fileName) {
        return new File("resources\\" + fileName);
    }

    public void createGraph(File file) {

        FileParser fileParser = new FileParser();
        fileParser.parse(file);
    }

    public void runQueries(String verilogFile) {
        String vfileName = verilogFile.substring(0, verilogFile.indexOf(".")) + ".csv";
        try (FileWriter csvFileWriter = new FileWriter("features\\" + vfileName)) {
            csvFileWriter.write("Netlist,LOFi1,LOFi2\n");
            for (String netName : graph.getAllNets()) {
                int lofi1 = query.fanIn(netName, 1);
                int logfi2 = query.fanIn(netName, 2);
                csvFileWriter.write(netName + "," + lofi1 + "," + logfi2 + "\n");
            }
            System.out.println("Features added to file succesfully!");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        FeatureExtract featureExtract = new FeatureExtract();
        String verilogFile = "s38584-T300.v";
        File file = featureExtract.openFile(verilogFile);
        featureExtract.createGraph(file);
        System.out.println("Graph created.");
        featureExtract.runQueries(verilogFile);
    }
}