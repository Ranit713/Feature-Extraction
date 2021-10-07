package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import components.Net;

/* Singleton class storing set of nets. */
class NetList {

    Map<String, Net> nets;
    Map<Net, Net> assignments;
    private static NetList singleInstance = null;

    private NetList() {
        nets = new HashMap<>();
        assignments = new HashMap<>();
    }

    static NetList getInstance() {
        if (singleInstance == null) // to ensure only one instance is created
            singleInstance = new NetList();
        return singleInstance;
    }

    void add(Net net) {
        String name = net.getName();
        nets.putIfAbsent(name, net);
    }

    void assign(String name1, String name2) {
        Net net1 = nets.get(name1);
        Net net2 = nets.get(name2);
        assignments.put(net1, net2);
    }

    /*
     * Retrieves the net using its name. If not found(few Trojan nets are noy
     * defined in wire), create new net and return the object.
     */
    Net get(String name) {
        if (!nets.containsKey(name)) {
            Net net = new Net(name);
            add(net);
        }
        return nets.get(name);
    }

    Set<Entry<String, Net>> getAllNets() {
        return nets.entrySet();
    }

    /* Prints all nets' name */
    void print() {
        for (Map.Entry<String, Net> net : nets.entrySet()) {
            String netName = net.getKey();
            System.out.println(netName);
        }
    }
}
