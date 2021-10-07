package queries;

import java.util.List;

import components.Net;
import components.SubModules;
import graphs.Graph;

class LogicFanIn {

    Graph graph;

    LogicFanIn() {
        graph = Graph.getInstance();
    }

    int fanInUptoLevel(String netName, int n) {
        Net net = graph.getNet(netName); // current net object
        String moduleName = net.getInput();
        if (moduleName == null)
            return 0;
        int fanIn = 0;
        SubModules module = graph.getModule(moduleName); // sub-module object at input side of net
        if (n == 1)
            return module.fanIn();
        List<String> inputNets = module.getInputs();
        for (String inputNet : inputNets)
            fanIn += fanInUptoLevel(inputNet, n - 1);
        return fanIn;
    }
}
