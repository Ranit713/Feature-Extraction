package queries;

import components.Net;

public class Connectivity {
    
    int getValue(Net net) {
        // Number of outputs and an input.
        return net.getOutputs().size() + 1;
    }
}
