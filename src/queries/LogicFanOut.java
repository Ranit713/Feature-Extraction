package queries;

import java.util.List;

import components.Net;
import components.SubModule;
import graphs.Graph;

public class LogicFanOut {

    Graph graph;

    LogicFanOut() {
        graph = Graph.getInstance();
    }

    int fanOutUptoLevel(Net net, int n) {
        List<SubModule> modules = net.getOutputs(); // sub-module objects at output side of net
        int fanOut = 0;
        for (SubModule module : modules) {
            if (n == 1)
                fanOut += module.fanOut();
            else {
                List<String> outputsNets = module.getOutputs();
                for (String outputNet : outputsNets)
                    fanOut += fanOutUptoLevel(graph.getNet(outputNet), n - 1);
            }
        }
        return fanOut;
    }
}
