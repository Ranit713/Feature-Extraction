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
}