package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

class LogicFanIO {

    private Set<String> visited;

    /** Initialize the "visited" set. */
    void init() {
        visited = new HashSet<>();
    }

    int fanInUptoLevel(Net net, int n) {
        visited.add(net.getName());
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module == null)
            return 0;
        int fanIn = 0;
        if (n == 1)
            return module.fanIn();
        for (Net inputNet : module.getInputs())
            if (!visited.contains(inputNet.getName()))
                fanIn += fanInUptoLevel(inputNet, n - 1);
        return fanIn;
    }

    int fanOutUptoLevel(Net net, int n) {
        visited.add(net.getName());
        int fanOut = 0;
        for (SubModule module : net.getOutputs()) {
            if (n == 1)
                fanOut += module.fanOut();
            else
                for (Net outputNet : module.getOutputs())
                    if (!visited.contains(outputNet.getName()))
                        fanOut += fanOutUptoLevel(outputNet, n - 1);
        }
        return fanOut;
    }

    int fanIn(Net net, int levels) {
        init();
        return fanInUptoLevel(net, levels);
    }

    int fanOut(Net net, int levels) {
        init();
        return fanOutUptoLevel(net, levels);
    }
}
