package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

public class FlipFlops {

    private Set<String> visited;

    /** Initialize the "visited" set. */
    void init() {
        visited = new HashSet<>();
    }

    int inFlipFlops(Net net, int n) {
        int flipFlops = 0;
        visited.add(net.getName());
        if (net.isPO()) {
            for (Net inputNet : net.assignedTo())
                flipFlops += inFlipFlops(inputNet, n);
        }
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module != null) {
            if (n == 0)
                return module.isFlipFlop() ? flipFlops + 1 : flipFlops;
            for (Net inputNet : module.getInputs())
                if (!visited.contains(inputNet.getName()))
                    flipFlops += inFlipFlops(inputNet, n - 1);
        }
        return flipFlops;
    }

    int outFlipFlops(Net net, int n) {
        int flipFlops = 0;
        visited.add(net.getName());
        if (net.isPI()) {
            for (Net outputNet : net.assignedTo())
                flipFlops += outFlipFlops(outputNet, n);
        }
        for (SubModule module : net.getOutputs()) {
            if (n == 0)
                return module.isFlipFlop() ? flipFlops + 1 : flipFlops;
            for (Net outputNet : module.getOutputs())
                if (!visited.contains(outputNet.getName()))
                    flipFlops += outFlipFlops(outputNet, n - 1);
        }
        return flipFlops;
    }

    int inFFPaths(Net net, int levels) {
        init();
        return inFlipFlops(net, levels);
    }

    int outFFPaths(Net net, int levels) {
        init();
        return outFlipFlops(net, levels);
    }
}