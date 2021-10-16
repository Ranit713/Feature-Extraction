package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;
import graphs.Graph;

public class PrimaryInput {

    Graph graph;
    Set<String> visited;

    PrimaryInput() {
        graph = Graph.getInstance();
    }

    /** Initialize the "visited" set. */
    void init() {
        visited = new HashSet<>();
    }

    /** Uses DFS to find minimum distance to any primary input from "net". */
    int dfs(Net net, int pi) {
        visited.add(net.getName());
        int min = Integer.MAX_VALUE; // stores minimum distance to any primary input
        SubModule inputModule = net.getInput(); // sub-module object at input side of net
        if (net.isInput() || inputModule == null) // if input of a wire is null, it means it is connected to a primary
                                                  // input
            return pi;
        for (String inputNet : inputModule.getInputs())
            if (!visited.contains(inputNet))
                min = Math.min(dfs(graph.getNet(inputNet), pi + 1), min);
        return min;
    }

    /** Returns the minimum distance to any primary input from a given net. */
    int minimumPI(Net net) {
        init();
        return dfs(net, 0);
    }
}
