package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.Net;
import components.SubModule;

public class Graph implements ModuleList, NetList {

    private Map<String, Net> nets; // list of nets
    private Map<Net, Net> assignments; // list of assignments
    private Map<String, SubModule> modules; // list of sub-modules
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
        nets.put(net.getName(), net);
    }

    /** Retrieve net object using net name. */
    public Net getNet(String name) {
        if (!nets.containsKey(name)) {
            // System.out.println(name);
            Net net = new Net(name);
            add(net);
        }
        return nets.get(name);
    }

    public List<Net> getAllNets() {
        List<Net> netlist = new ArrayList<>();
        for (Net net : nets.values()) {
            netlist.add(net);
        }
        return netlist;
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
    public void add(SubModule module) {
        String name = module.getName();
        modules.putIfAbsent(name, module);
    }

    public SubModule getModule(String name) {
        return modules.get(name);
    }

    public List<SubModule> getAllModules() {
        List<SubModule> modulelist = new ArrayList<>();
        for (SubModule module : modules.values())
            modulelist.add(module);
        return modulelist;
    }

    /* Assigment functionality. */
    public void assign(String name1, String name2) {
        Net net1 = nets.get(name1);
        Net net2 = nets.get(name2);
        assignments.put(net1, net2);
    }
}
