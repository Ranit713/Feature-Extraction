package queries;

import components.Net;
import components.SubModule;

public class FlipFlops {

    int inFlipFlops(Net net, int n) {
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module == null)
            return 0;
        int flipFlops = 0;
        if (n == 0)
            return module.isFlipFlop() ? flipFlops + 1 : flipFlops;
        for (Net inputNet : module.getInputs())
            flipFlops += inFlipFlops(inputNet, n - 1);
        return flipFlops;
    }

    int outFlipFlops(Net net, int n) {
        int flipFlops = 0;
        if (net.isPI()) {
            for (Net outputNet : net.assignedTo())
                flipFlops += outFlipFlops(outputNet, n);
        }
        for (SubModule module : net.getOutputs()) {
            if (n == 0) {
                if (module.isFlipFlop())
                    return flipFlops + 1;
                else
                    return flipFlops;
                // return module.isFlipFlop() ? flipFlops + 1 : flipFlops;
            }
            for (Net outputNet : module.getOutputs())
                flipFlops += outFlipFlops(outputNet, n - 1);
        }
        return flipFlops;
    }
}