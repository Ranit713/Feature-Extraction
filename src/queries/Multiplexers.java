package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

public class Multiplexers {

    private Set<String> visited;

    /** Initialize the "visited" set. */
    void init() {
        visited = new HashSet<>();
    }

    int inMUX(Net net, int n) {
        visited.add(net.getName());
        int mux = 0;
        if (net.isPO()) {
            for (Net inputNet : net.assignedTo())
                mux += inMUX(inputNet, n);
        }
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module != null) {
            if (n == 0)
                return module.isMultiplexer() ? mux + 1 : mux;
            for (Net inputNet : module.getInputs())
                if (!visited.contains(inputNet.getName()))
                    mux += inMUX(inputNet, n - 1);
        }
        return mux;
    }

    int outMUX(Net net, int n) {
        visited.add(net.getName());
        int mux = 0;
        if (net.isPI()) {
            for (Net outputNet : net.assignedTo())
                mux += outMUX(outputNet, n);
        }
        for (SubModule module : net.getOutputs()) {
            if (n == 0)
                return module.isMultiplexer() ? mux + 1 : mux;
            for (Net outputNet : module.getOutputs())
                if (!visited.contains(outputNet.getName()))
                    mux += outMUX(outputNet, n - 1);
        }
        return mux;
    }

    int inMUXPaths(Net net, int levels) {
        init();
        return inMUX(net, levels);
    }

    int outMUXPaths(Net net, int levels) {
        init();
        return outMUX(net, levels);
    }
}