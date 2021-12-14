package queries;

import components.Net;

public class Query {

    private LogicFanIn logicFanIn;
    private LogicFanOut logicFanOut;
    private PrimaryIO primaryIO;
    private FlipFlops ff;
    private Multiplexers mux;
    private Loops loop;
    private Connectivity connectivity;

    public Query() {
        logicFanIn = new LogicFanIn();
        logicFanOut = new LogicFanOut();
        primaryIO = new PrimaryIO();
        ff = new FlipFlops();
        mux = new Multiplexers();
        loop = new Loops();
        connectivity = new Connectivity();
    }

    public int fanIn(Net net, int level) {
        return logicFanIn.fanInUptoLevel(net, level);
    }

    public int fanOut(Net net, int level) {
        return logicFanOut.fanOutUptoLevel(net, level);
    }

    public int primaryInput(Net net) {
        return primaryIO.minimumPI(net);
    }

    public int primaryOutput(Net net) {
        return primaryIO.minimumPO(net);
    }

    public int inFlipFlop(Net net, int level) {
        return ff.inFlipFlops(net, level);
    }

    public int inMultiplexer(Net net, int level) {
        return mux.inMUX(net, level);
    }

    public int inloopUptoLevel(Net net, int level) {
        int loops = 0;
        for (int i = 1; i <= level; i++)
            loops += loop.inLoopx(net, i);
        return loops;
    }

    public int connectivity(Net net) {
        return connectivity.getValue(net);
    }
}
