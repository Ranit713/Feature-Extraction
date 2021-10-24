package queries;

import components.Net;
import components.SubModule;
import graphs.Graph;

public class Multiplexers {

    Graph graph;

    Multiplexers() {
        graph = Graph.getInstance();
    }

    int inMUX(Net net, int n) {
        SubModule module = net.getInput(); // sub-module object at input side of net
        if (module == null)
            return 0;
        int mux = 0;
        if (n == 0)
            return module.isMultiplexer() ? mux + 1 : mux;
        for (Net inputNet : module.getInputs())
            mux += inMUX(inputNet, n - 1);
        return mux;
    }
}