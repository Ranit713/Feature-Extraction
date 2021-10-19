package parsers.parser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import components.Net;
import graphs.Graph;

public class NetlistToken {

    private Graph graph;
    private final String regex;

    public NetlistToken() {
        graph = Graph.getInstance();
        regex = "\\[\\s*\\d*\\s*:\\s*\\d*\\s*]\\s*"; // regex to match pattern [23:0] wire_name(example)
    }

    /** Parses tokens according to nets' definitions. */
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
        int ports = ports(netlists);
        if (ports > 0)
            multiportNetToken(netlists, type, ports);
        else
            singlePortNetToken(netlists, type);
    }

    /** Calculates number of input ports in the net. */
    private int ports(String net) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(net);
        int wires = 0;
        if (m.find()) {
            String num = m.group(0).split(":")[0].replaceAll("[^\\d]", " ").trim(); // extract "23" from it
            wires = Integer.parseInt(num);
        }
        return wires;
    }

    /** Creates nets having a single input port. */
    private void singlePortNetToken(String netlists, char type) {
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

    /** Creates nets having multiple input ports. */
    private void multiportNetToken(String netlists, char type, int ports) {
        String netName = netlists.split(regex)[1];
        for (int i = 0; i <= ports; i++) {
            String netNameIndex = netName + "[" + i + "]";
            Net net = new Net(netNameIndex);
            net.setType(type);
            graph.add(net);
        }
    }
}
