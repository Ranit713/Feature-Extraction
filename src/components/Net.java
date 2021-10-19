package components;

import java.util.ArrayList;
import java.util.List;

public class Net {

    private String netName;
    private SubModule inputModule;
    private List<SubModule> outputs;
    private List<String> assignedTo;
    private char netType;

    public Net(String name) {
        netName = name;
        inputModule = null;
        outputs = new ArrayList<>();
        assignedTo = new ArrayList<>();
        netType = 'w';
    }

    /** Set input of the net. */
    public void setInput(SubModule moduleName) {
        inputModule = moduleName;
    }

    /** Add modules on the output side if the net. */
    public void addOutputs(SubModule moduleName) {
        outputs.add(moduleName);
    }

    /** 'i' for Input; 'o' for Output; 'w' for wire. */
    public void setType(char type) {
        netType = type;
    }

    public char getType() {
        return netType;
    }

    /** Returns name of the net. */
    public String getName() {
        return netName;
    }

    /** Returns name of the module current net is coming from. */
    public SubModule getInput() {
        return inputModule;
    }

    /** Returns list of the modules names current net is going into. */
    public List<SubModule> getOutputs() {
        return outputs;
    }

    /** Returns true if current net is a wire. */
    public boolean isWire() {
        return netType == 'w';
    }

    /** Returns true if current net is a primary input. */
    public boolean isInput() {
        return netType == 'i';
    }

    /** Returns true if current net is a primary output. */
    public boolean isOutput() {
        return netType == 'o';
    }

    public void assignTo(String netName) {
        assignedTo.add(netName);
    }

    public List<String> assignedTo() {
        return assignedTo;
    }
}
