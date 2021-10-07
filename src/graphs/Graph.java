package graphs;

import java.util.Set;
import java.util.Map.Entry;

import components.Net;
import components.SubModules;

public class Graph {

    private static Graph singleInstance = null;
    private NetList netList;
    private ModuleList moduleList;

    private Graph() {
        netList = NetList.getInstance();
        moduleList = ModuleList.getInstance();
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
        netList.add(net);
    }

    public Net getNet(String netName) {
        return netList.get(netName);
    }

    public Set<Entry<String, Net>> getAllNets() {
        return netList.getAllNets();
    }

    public void showNetlist() {
        netList.print();
    }

    /*
     * Module list functionalities listed below.
     */
    public void add(SubModules module) {
        moduleList.add(module);
    }

    public SubModules getModule(String moduleName) {
        return moduleList.get(moduleName);
    }

    /* Assigment functionality. */
    public void assign(String name1, String name2) {
        netList.assign(name1, name2);
    }
}
