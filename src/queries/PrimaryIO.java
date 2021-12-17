package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

public class PrimaryIO {

    private Set<String> visited;

    /** Initialize the "visited" set. */
    void init() {
        visited = new HashSet<>();
    }

    /** Uses DFS to find minimum distance to any primary input from "net". */
    int dfsPI(Net net, int pi) {
        visited.add(net.getName());
        if (net.isPI() || net.assignedToPI())
            return pi;

        int min = Integer.MAX_VALUE; // stores minimum distance to any primary input
        SubModule inputModule = net.getInput(); // sub-module object at input side of net

        if (net.isPO()) {
            for (Net inputNet : net.assignedTo())
                min = Math.min(dfsPI(inputNet, pi), min);
        }
        if (inputModule != null) {
            for (Net inputNet : inputModule.getInputs())
                if (!visited.contains(inputNet.getName()))
                    min = Math.min(dfsPI(inputNet, pi + 1), min);
        }
        return min;
    }

    /** Uses DFS to find minimum distance to any primary output from "net". */
    int dfsPO(Net net, int po) {
        if (net.isPO() || net.assignedToPO())
            return po;

        visited.add(net.getName());
        int min = Integer.MAX_VALUE; // stores minimum distance to any primary input

        // if net has no sub-module outputs, there must be an assignment to it.
        if (net.isPI()) {
            for (Net wire : net.assignedTo())
                min = Math.min(dfsPO(wire, po), min);
        }
        for (SubModule outputModule : net.getOutputs()) {
            for (Net outputNet : outputModule.getOutputs())
                if (!visited.contains(outputNet.getName()))
                    min = Math.min(dfsPO(outputNet, po + 1), min);
        }
        return min;
    }

    /** Returns the minimum distance to any primary input from a given net. */
    int minimumPI(Net net) {
        init();
        return dfsPI(net, 0);
    }

    /** Returns the minimum distance to any primary output from a given net. */
    int minimumPO(Net net) {
        init();
        return dfsPO(net, 0);
    }
}
