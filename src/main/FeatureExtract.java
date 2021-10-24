package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import components.Net;
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

    /** Opens input file. */
    public File openFile(String fileName) {
        return new File("../resources/" + fileName);
    }

    /** Creates the circuit graph. */
    public void createGraph(File file) {
        FileParser fileParser = new FileParser();
        fileParser.parse(file);
        System.out.println("Graph created.");
    }

    /** Runs queries and stores feature values in a csv file. */
    public void runQueries(String verilogFile) {
        String vfileName = verilogFile.substring(0, verilogFile.indexOf(".")) + ".csv"; // csv file using the original
                                                                                        // file name
        try (FileWriter csvFileWriter = new FileWriter("../features/" + vfileName)) {
            csvFileWriter.write("Netlist,LOFi1,LOFi2,LOFo1,LOFo2,PI,inFF1,inFF2,inMUX1,inMUX2\n");

            // Queries run below.
            for (Net net : graph.getAllNets()) {

                String netName = net.getName();
                int lofi1 = query.fanIn(net, 1);
                int logfi2 = query.fanIn(net, 2);
                int logfo1 = query.fanOut(net, 1);
                int logfo2 = query.fanOut(net, 2);
                int pi = query.primaryInput(net);
                int inFF1 = query.inFlipFlop(net, 1);
                int inFF2 = query.inFlipFlop(net, 2);
                int inMUX1 = query.inMultiplexer(net, 1);
                int inMUX2 = query.inMultiplexer(net, 2);

                // System.out.println(netName + "," + lofi1 + "," + logfi2 + "," + logfo1 + ","
                // + pi + "\n");

                csvFileWriter.write(netName + "," + lofi1 + "," + logfi2 + "," + logfo1 + "," + logfo2 + "," + pi + ","
                        + inFF1 + "," + inFF2 + "," + inMUX1 + "," + inMUX2 + "\n");
            }
            System.out.println("Features added to file succesfully!");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        FeatureExtract featureExtract = new FeatureExtract();
        String verilogFile = "RS232-T1000.v";
        File file = featureExtract.openFile(verilogFile);
        featureExtract.createGraph(file);
        featureExtract.runQueries(verilogFile);
    }
}
