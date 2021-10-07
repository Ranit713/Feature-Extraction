package components;

import java.util.ArrayList;
import java.util.List;

public class Net {

    private String netName;
    private String input;
    private List<String> outputs;
    private char netType;

    public Net(String name) {
        netName = name;
        input = null;
        outputs = new ArrayList<>();
        netType = 'w';
    }

    public void setInput(String moduleName) {
        input = moduleName;
    }

    public void addOutputs(String moduleName) {
        outputs.add(moduleName);
    }

    /*
     * 'i' for Input; 'o' for Output; 'w' for wire
     */
    public void setType(char type) {
        netType = type;
    }

    public String getName() {
        return netName;
    }

    public String getInput() {
        return input;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public char getType() {
        return netType;
    }
}
