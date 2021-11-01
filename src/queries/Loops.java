package queries;

import components.Net;
import components.SubModule;

public class Loops {

    /** Uses DFS to find cycle of size n involving target net. */
    int dfs(Net curr, Net target, int n) {
        if (n == 0)
            return curr == target ? 1 : 0;
        SubModule inputModule = curr.getInput();
        if (inputModule == null)
            return 0;
        int loops = 0;
        for (Net net : inputModule.getInputs())
            loops += dfs(net, target, n - 1);
        return loops;
    }

    int inLoopx(Net net, int level) {
        return dfs(net, net, level);
    }
}
