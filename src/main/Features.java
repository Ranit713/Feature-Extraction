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

    private StringBuilder HEADER;
    private String netName;

    private int lofi1;
    private int lofi2;
    private int lofi3;
    private int lofi4;
    private int lofi5;

    private int logfo1;
    private int logfo2;
    private int logfo3;
    private int logfo4;
    private int logfo5;

    private int pi;
    private int po;

    private int inFF1;
    private int inFF2;
    private int inFF3;
    private int inFF4;
    private int inFF5;

    private int inMUX1;
    private int inMUX2;
    private int inMUX3;
    private int inMUX4;
    private int inMUX5;

    private int inloop;

    private int connectitvity;

    Features() {
        graph = Graph.getInstance();
        query = new Query();
        data = new HashMap<>();
        HEADER = new StringBuilder();
        HEADER.append("Netlist,");
        HEADER.append("LOFi1,LOFi2,LOFi3,LOFi4,LOFi5,");
        HEADER.append("LOFo1,LOFo2,LOFo3,LOFo4,LOFo5,");
        HEADER.append("PI,PO,");
        HEADER.append("inFF1,inFF2,inFF3,inFF4,inFF5,");
        HEADER.append("inMUX1,inMUX2,inMUX3,inMUX4,inMUX5,");
        HEADER.append("inLoop,Connectivity\n");
    }

    private void setData(Net net) {
        netName = net.getName();

        lofi1 = query.fanIn(net, 1);
        lofi2 = query.fanIn(net, 2);
        lofi3 = query.fanIn(net, 3);
        lofi4 = query.fanIn(net, 4);
        lofi5 = query.fanIn(net, 5);

        logfo1 = query.fanOut(net, 1);
        logfo2 = query.fanOut(net, 2);
        logfo3 = query.fanOut(net, 3);
        logfo4 = query.fanOut(net, 4);
        logfo5 = query.fanOut(net, 5);

        pi = query.primaryInput(net);
        po = query.primaryOutput(net);

        inFF1 = query.inFlipFlop(net, 1);
        inFF2 = query.inFlipFlop(net, 2);
        inFF3 = query.inFlipFlop(net, 3);
        inFF4 = query.inFlipFlop(net, 4);
        inFF5 = query.inFlipFlop(net, 5);

        inMUX1 = query.inMultiplexer(net, 1);
        inMUX2 = query.inMultiplexer(net, 2);
        inMUX3 = query.inMultiplexer(net, 3);
        inMUX4 = query.inMultiplexer(net, 4);
        inMUX5 = query.inMultiplexer(net, 5);

        inloop = query.inloopUptoLevel(net, 3);
        connectitvity = query.connectivity(net);
    }

    private void addData() {
        features = new ArrayList<>();

        features.add(lofi1);
        features.add(lofi2);
        features.add(lofi3);
        features.add(lofi4);
        features.add(lofi5);

        features.add(logfo1);
        features.add(logfo2);
        features.add(logfo3);
        features.add(logfo4);
        features.add(logfo5);

        features.add(pi);
        features.add(po);

        features.add(inFF1);
        features.add(inFF2);
        features.add(inFF3);
        features.add(inFF4);
        features.add(inFF5);

        features.add(inMUX1);
        features.add(inMUX2);
        features.add(inMUX3);
        features.add(inMUX4);
        features.add(inMUX5);

        features.add(inloop);

        features.add(connectitvity);

        data.put(netName, features);
    }

    /** Returns the list of headers to be added in CSV. */
    String getHeader() {
        return HEADER.toString();
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
