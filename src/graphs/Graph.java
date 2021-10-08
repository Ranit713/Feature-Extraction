package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import components.Net;
import components.SubModules;

public class Graph implements ModuleList, NetList {

    Map<String, Net> nets;
    Map<Net, Net> assignments;
    Map<String, SubModules> modules;
    private static Graph singleInstance = null;

    private Graph() {
        nets = new HashMap<>();
        assignments = new HashMap<>();
        modules = new HashMap<>();
    }

    public static Graph getInstance() {
        if (singleInstance == null) // to ensure only one instance is created
            singleInstance = new Graph();
        return singleInstance;
    }

    /*
     * Net list functionalities listed below
     */
    public void add(Net net) {
        String name = net.getName();
        nets.putIfAbsent(name, net);
    }

    public Net getNet(String name) {
        if (!nets.containsKey(name)) {
            Net net = new Net(name);
            add(net);
        }
        return nets.get(name);
    }

    public Set<Entry<String, Net>> getAllNets() {
        return nets.entrySet();
    }

    public void showNetlist() {
        for (Map.Entry<String, Net> net : nets.entrySet()) {
            String netName = net.getKey();
            System.out.println(netName);
        }
    }

    /*
     * Module list functionalities listed below.
     */
    public void add(SubModules module) {
        String name = module.getName();
        modules.putIfAbsent(name, module);
    }

    public SubModules getModule(String name) {
        return modules.get(name);
    }

    /* Assigment functionality. */
    public void assign(String name1, String name2) {
        Net net1 = nets.get(name1);
        Net net2 = nets.get(name2);
        assignments.put(net1, net2);
    }
}
