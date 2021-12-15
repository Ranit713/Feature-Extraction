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

    private int n;
    private StringBuilder HEADER;
    private String netName;

    private int[] logfi;
    private int[] logfo;
    private int[] inFF;
    private int[] inMUX;

    private int pi;
    private int po;
    private int inloop;
    private int connectitvity;

    Features(int x) {
        graph = Graph.getInstance();
        query = new Query();
        data = new HashMap<>();
        HEADER = new StringBuilder();

        n = x;
        logfi = new int[n];
        logfo = new int[n];
        inFF = new int[n];
        inMUX = new int[n];

        HEADER.append("Netlist,");
        HEADER.append("LOFi1,LOFi2,LOFi3,LOFi4,LOFi5,");
        HEADER.append("LOFo1,LOFo2,LOFo3,LOFo4,LOFo5,");
        HEADER.append("inFF1,inFF2,inFF3,inFF4,inFF5,");
        HEADER.append("inMUX1,inMUX2,inMUX3,inMUX4,inMUX5,");
        HEADER.append("PI,PO,");
        HEADER.append("inLoop,Connectivity\n");
    }

    private void setData(Net net) {
        netName = net.getName();
        for (int i = 0; i < n; i++) {
            logfi[i] = query.fanIn(net, i + 1);
            logfo[i] = query.fanOut(net, i + 1);
            inFF[i] = query.inFlipFlop(net, i + 1);
            inMUX[i] = query.inMultiplexer(net, i + 1);
        }
        pi = query.primaryInput(net);
        po = query.primaryOutput(net);
        inloop = query.inloopUptoLevel(net, n);
        connectitvity = query.connectivity(net);
    }

    private void addData() {
        features = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            features.add(logfi[i]);
            features.add(logfo[i]);
            features.add(inFF[i]);
            features.add(inMUX[i]);
        }
        features.add(pi);
        features.add(po);
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
