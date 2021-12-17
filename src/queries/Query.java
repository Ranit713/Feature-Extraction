package queries;

import components.Net;

public class Query {

    private LogicFanIO logicFanIO;
    private PrimaryIO primaryIO;
    private FlipFlops ff;
    private Multiplexers mux;
    private Loops loop;
    private Connectivity connectivity;

    public Query() {
        logicFanIO = new LogicFanIO();
        primaryIO = new PrimaryIO();
        ff = new FlipFlops();
        mux = new Multiplexers();
        loop = new Loops();
        connectivity = new Connectivity();
    }

    public int fanInOut(Net net, int level, boolean inOut) {
        return inOut ? logicFanIO.fanIn(net, level) : logicFanIO.fanOut(net, level);
    }

    public int primaryInOut(Net net, boolean inOut) {
        return inOut ? primaryIO.minimumPI(net) : primaryIO.minimumPO(net);
    }

    /** Calculates number of paths to all flip-flops at a given level. */
    public int inOutFlipFlop(Net net, int level, boolean inOut) {
        return inOut ? ff.inFFPaths(net, level) : ff.outFFPaths(net, level);
    }

    /** Calculates number of paths to all multiplexers at a given level. */
    public int inOutMultiplexer(Net net, int level, boolean inOut) {
        return inOut ? mux.inMUXPaths(net, level) : mux.outMUXPaths(net, level);
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
