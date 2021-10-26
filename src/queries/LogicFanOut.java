package queries;

import java.util.List;

import components.Net;
import components.SubModule;

public class LogicFanOut {

    int fanOutUptoLevel(Net net, int n) {
        List<SubModule> modules = net.getOutputs(); // sub-module objects at output side of net
        int fanOut = 0;
        for (SubModule module : modules) {
            if (n == 1)
                fanOut += module.fanOut();
            else
                for (Net outputNet : module.getOutputs())
                    fanOut += fanOutUptoLevel(outputNet, n - 1);
        }
        return fanOut;
    }
}
