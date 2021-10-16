package queries;

import components.Net;

public class Query {

    LogicFanIn logicFanIn;
    PrimaryInput primaryInputObj;

    public Query() {
        logicFanIn = new LogicFanIn();
        primaryInputObj = new PrimaryInput();
    }

    public int fanIn(String netName, int level) {
        return logicFanIn.fanInUptoLevel(netName, level);
    }

    public int primaryInput(Net net) {
        return primaryInputObj.minimumPI(net);
    }
}
