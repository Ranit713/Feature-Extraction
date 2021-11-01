package queries;

import components.Net;

public class Connectivity {
    
    int getValue(Net net) {
        return net.getOutputs().size();
    }
}
