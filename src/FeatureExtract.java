import java.io.File;

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

    public void runQueries() {
        query.fanIn(2);
    }

    public static void main(String[] args) {
        FeatureExtract featureExtract = new FeatureExtract();
        String fileName = "s38584-T300.v";
        File file = featureExtract.openFile(fileName);
        featureExtract.createGraph(file);
        featureExtract.runQueries();
    }
}