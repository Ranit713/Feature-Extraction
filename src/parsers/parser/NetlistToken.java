package parsers.parser;

import java.util.Scanner;

import components.Net;
import graphs.Graph;

public class NetlistToken {

    private Graph graph;

    public NetlistToken() {
        graph = Graph.getInstance();
    }

    /** Parses tokens with nets' definitions. */
    public boolean parse(String token) {
        if (token.startsWith("input")) {
            String inputs = token.split("input\\s*")[1];
            netlistToken(inputs, 'i');
        } else if (token.startsWith("output")) {
            String outputs = token.split("output\\s*")[1];
            netlistToken(outputs, 'o');
        } else if (token.startsWith("wire")) {
            String wires = token.split("wire\\s*")[1];
            netlistToken(wires, 'w');
        } else // anything other than "input", "output", and "wire" does not belong to net
               // definition
            return false;
        return true;
    }

    /** Stores a net as a graph edge. */
    private void netlistToken(String netlists, char type) {
        try (Scanner sc = new Scanner(netlists).useDelimiter("\\s*,\\s*")) {
            while (sc.hasNext()) {
                String netName = sc.next();
                Net net = new Net(netName);
                net.setType(type);
                graph.add(net);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
