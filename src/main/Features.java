package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.Net;
import graphs.Graph;
import queries.Query;

class Features {

    private Graph graph;
    private Query query;
    private List<Integer> features;
    private Map<String, List<Integer>> data;

    private String HEADER;
    private String netName;
    private int lofi1;
    private int logfi2;
    private int logfo1;
    private int logfo2;
    private int pi;
    private int inFF1;
    private int inFF2;
    private int inMUX1;
    private int inMUX2;
    private int inloop;
    private int connectitvity;

    Features() {
        graph = Graph.getInstance();
        query = new Query();
        data = new HashMap<>();
        HEADER = "Netlist,LOFi1,LOFi2,LOFo1,LOFo2,PI,inFF1,inFF2,inMUX1,inMUX2,inLoop,Connectivity\n";
    }

    private void setData(Net net) {
        netName = net.getName();
        lofi1 = query.fanIn(net, 1);
        logfi2 = query.fanIn(net, 2);
        logfo1 = query.fanOut(net, 1);
        logfo2 = query.fanOut(net, 2);
        pi = query.primaryInput(net);
        inFF1 = query.inFlipFlop(net, 1);
        inFF2 = query.inFlipFlop(net, 2);
        inMUX1 = query.inMultiplexer(net, 1);
        inMUX2 = query.inMultiplexer(net, 2);
        inloop = query.inloopUptoLevel(net, 3);
        connectitvity = query.connectivity(net);
    }

    private void addData() {
        features = new ArrayList<>();
        features.add(lofi1);
        features.add(logfi2);
        features.add(logfo1);
        features.add(logfo2);
        features.add(pi);
        features.add(inFF1);
        features.add(inFF2);
        features.add(inMUX1);
        features.add(inMUX2);
        features.add(inloop);
        features.add(connectitvity);
        data.put(netName, features);
    }

    /** Returns the list of headers to be added in CSV. */
    String getHeader() {
        return HEADER;
    }

    /** Returns the extracted feature values of each net. */
    Map<String, List<Integer>> extract() {
        for (Net net : graph.getAllNets()) {
            setData(net);
            addData();
        }
        return data;
    }
}
