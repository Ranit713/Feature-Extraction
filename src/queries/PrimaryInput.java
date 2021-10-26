package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

public class PrimaryInput {

    private Set<String> visited;

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
        for (Net inputNet : inputModule.getInputs())
            if (!visited.contains(inputNet.getName()))
                min = Math.min(dfs(inputNet, pi + 1), min);
        return min;
    }

    /** Returns the minimum distance to any primary input from a given net. */
    int minimumPI(Net net) {
        init();
        return dfs(net, 0);
    }
}
