package queries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import components.Net;
import components.SubModule;
import graphs.Graph;

public class PrimaryInput {

    Graph graph;
    Map<String, Integer> minPrimaryInput = new HashMap<>();
    Set<String> visited = new HashSet<>();

    PrimaryInput() {
        graph = Graph.getInstance();
        minPrimaryInput = new HashMap<>();
    }

    int dfs(Net net, int pi) {
        visited.add(net.getName());
        int min = Integer.MAX_VALUE;
        SubModule inputModule = net.getInput(); // sub-module object at input side of net
        if (net.isInput() || inputModule == null) // if input of a wire is null, it means it is connected to a primary
                                                  // input
            return pi;
        for (String inputNet : inputModule.getInputs())
            if (!visited.contains(inputNet))
                min = Math.min(dfs(graph.getNet(inputNet), pi + 1), min);
        return min;
    }
}
