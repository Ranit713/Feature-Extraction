package queries;

import java.util.List;

import components.Net;
import components.SubModule;
import graphs.Graph;

class LogicFanIn {

    Graph graph;

    LogicFanIn() {
        graph = Graph.getInstance();
    }

    int fanInUptoLevel(String netName, int n) {
        Net net = graph.getNet(netName); // current net object
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module == null)
            return 0;
        int fanIn = 0;
        if (n == 1)
            return module.fanIn();
        List<String> inputNets = module.getInputs();
        for (String inputNet : inputNets)
            fanIn += fanInUptoLevel(inputNet, n - 1);
        return fanIn;
    }
}
