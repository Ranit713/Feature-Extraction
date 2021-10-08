package queries;

public class Query {

    LogicFanIn logicFanIn;

    public Query() {
        logicFanIn = new LogicFanIn();
    }

    public int fanIn(String netName, int level) {
        return logicFanIn.fanInUptoLevel(netName, level);
    }
}
