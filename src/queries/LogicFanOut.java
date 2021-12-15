package queries;

import components.Net;
import components.SubModule;

public class LogicFanOut {

    int fanOutUptoLevel(Net net, int n) {
        int fanOut = 0;
        for (SubModule module : net.getOutputs()) {
            if (n == 1)
                fanOut += module.fanOut();
            else
                for (Net outputNet : module.getOutputs())
                    fanOut += fanOutUptoLevel(outputNet, n - 1);
        }
        return fanOut;
    }
}
