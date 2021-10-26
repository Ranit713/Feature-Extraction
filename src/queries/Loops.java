package queries;

import java.util.HashSet;
import java.util.Set;

import components.Net;
import components.SubModule;

public class Loops {

    private int loops;
    private Set<String> visited;
    private int level_;

    void init(int level) {
        loops = 0;
        level_ = level;
        visited = new HashSet<>();
    }

    /** Uses DFS to find minimum distance to any primary input from "net". */
    int dfs(Net net, int x) {
        System.out.println(net.getName());
        visited.add(net.getName());
        SubModule inputModule = net.getInput();
        if (inputModule == null || x > level_)
            return 0;
        for (Net inputNet : inputModule.getInputs()) {
            if (!visited.contains(inputNet.getName())) 
                loops += dfs(inputNet, x + 1);
            else if (x == level_) {
                System.out.println(net.getName());
                loops++;
            }
        }
        return loops;
    }

    int inLoopx(Net net, int level) {
        init(level);
        return dfs(net, 0);
    }
}
