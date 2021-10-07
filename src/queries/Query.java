package queries;

import java.util.Map.Entry;

import components.Net;
import graphs.Graph;

public class Query {

    Graph graph;
    LogicFanIn logicFanIn;

    public Query() {
        graph = Graph.getInstance();
        logicFanIn = new LogicFanIn();
    }

    public void fanIn(int level) {
        for (Entry<String, Net> netListEntry : graph.getAllNets()) {
            String netName = netListEntry.getKey();
            Net net = netListEntry.getValue();
            if (net.getType() == 'w')
                System.out.println(netName + ", " + logicFanIn.fanInUptoLevel(netName, level));
        }
    }
}
