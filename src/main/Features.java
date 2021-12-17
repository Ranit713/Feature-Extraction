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

    private int outFF1;
    private int outFF2;
    private int outFF3;
    private int outFF4;
    private int outFF5;

    private int inMUX1;
    private int inMUX2;
    private int inMUX3;
    private int inMUX4;
    private int inMUX5;

    private int outMUX1;
    private int outMUX2;
    private int outMUX3;
    private int outMUX4;
    private int outMUX5;

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
        HEADER.append("outFF1,outFF2,outFF3,outFF4,outFF5,");
        HEADER.append("inMUX1,inMUX2,inMUX3,inMUX4,inMUX5,");
        HEADER.append("outMUX1,outMUX2,outMUX3,outMUX4,outMUX5,");
        HEADER.append("inLoop,Connectivity\n");
    }

    private void setData(Net net) {
        netName = net.getName();

        lofi1 = query.fanInOut(net, 1, true);
        lofi2 = query.fanInOut(net, 2, true);
        lofi3 = query.fanInOut(net, 3, true);
        lofi4 = query.fanInOut(net, 4, true);
        lofi5 = query.fanInOut(net, 5, true);

        logfo1 = query.fanInOut(net, 1, false);
        logfo2 = query.fanInOut(net, 2, false);
        logfo3 = query.fanInOut(net, 3, false);
        logfo4 = query.fanInOut(net, 4, false);
        logfo5 = query.fanInOut(net, 5, false);

        pi = query.primaryInOut(net, true);
        po = query.primaryInOut(net, false);

        inFF1 = query.inOutFlipFlop(net, 1, true);
        inFF2 = query.inOutFlipFlop(net, 2, true);
        inFF3 = query.inOutFlipFlop(net, 3, true);
        inFF4 = query.inOutFlipFlop(net, 4, true);
        inFF5 = query.inOutFlipFlop(net, 5, true);

        outFF1 = query.inOutFlipFlop(net, 1, false);
        outFF2 = query.inOutFlipFlop(net, 2, false);
        outFF3 = query.inOutFlipFlop(net, 3, false);
        outFF4 = query.inOutFlipFlop(net, 4, false);
        outFF5 = query.inOutFlipFlop(net, 5, false);

        inMUX1 = query.inOutMultiplexer(net, 1, true);
        inMUX2 = query.inOutMultiplexer(net, 2, true);
        inMUX3 = query.inOutMultiplexer(net, 3, true);
        inMUX4 = query.inOutMultiplexer(net, 4, true);
        inMUX5 = query.inOutMultiplexer(net, 5, true);

        outMUX1 = query.inOutMultiplexer(net, 1, false);
        outMUX2 = query.inOutMultiplexer(net, 2, false);
        outMUX3 = query.inOutMultiplexer(net, 3, false);
        outMUX4 = query.inOutMultiplexer(net, 4, false);
        outMUX5 = query.inOutMultiplexer(net, 5, false);

        inloop = query.inloopUptoLevel(net, 5);
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

        features.add(outFF1);
        features.add(outFF2);
        features.add(outFF3);
        features.add(outFF4);
        features.add(outFF5);

        features.add(inMUX1);
        features.add(inMUX2);
        features.add(inMUX3);
        features.add(inMUX4);
        features.add(inMUX5);

        features.add(outMUX1);
        features.add(outMUX2);
        features.add(outMUX3);
        features.add(outMUX4);
        features.add(outMUX5);

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