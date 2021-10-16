package queries;

import java.util.Map;

import components.Net;

public class Query {

    LogicFanIn logicFanIn;
    PrimaryInput primaryInputObj;
    Map<String, Integer> minPrimaryInput;

    public Query() {
        logicFanIn = new LogicFanIn();
        // minPrimaryInput = primaryInputObj.primaryInput();
    }

    public int fanIn(String netName, int level) {
        return logicFanIn.fanInUptoLevel(netName, level);
    }

    public int primaryInput(Net net) {
        primaryInputObj = new PrimaryInput();
        return primaryInputObj.dfs(net, 0);
    }
}
