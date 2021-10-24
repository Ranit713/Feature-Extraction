package queries;

import components.Net;

public class Query {

    LogicFanIn logicFanIn;
    LogicFanOut logicFanOut;
    PrimaryInput primaryInputObj;
    FlipFlops ff;
    Multiplexers mux;

    public Query() {
        logicFanIn = new LogicFanIn();
        logicFanOut = new LogicFanOut();
        primaryInputObj = new PrimaryInput();
        ff = new FlipFlops();
        mux = new Multiplexers();
    }

    public int fanIn(Net net, int level) {
        return logicFanIn.fanInUptoLevel(net, level);
    }

    public int fanOut(Net net, int level) {
        return logicFanOut.fanOutUptoLevel(net, level);
    }

    public int primaryInput(Net net) {
        return primaryInputObj.minimumPI(net);
    }

    public int inFlipFlop(Net net, int level) {
        return ff.inFlipFlops(net, level);
    }

    public int inMultiplexer(Net net, int level) {
        return mux.inMUX(net, level);
    }
}
