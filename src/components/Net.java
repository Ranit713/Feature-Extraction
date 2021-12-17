package components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Net {

    private String netName;
    private SubModule inputModule;
    private List<SubModule> outputs;
    private List<Net> assignedTo;
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

    /** Returns set of the modules names current net is going into. */
    public Set<SubModule> getOutputs() {
        return new HashSet<>(outputs);
    }

    /** Returns true if current net is a wire. */
    public boolean isWire() {
        return netType == 'w';
    }

    /** Returns true if current net is a primary input. */
    public boolean isPI() {
        return netType == 'i';
    }

    /** Returns true if current net is a primary output. */
    public boolean isPO() {
        return netType == 'o';
    }

    /** Performs assignment operation for the current net. */
    public void assignTo(Net net) {
        assignedTo.add(net);
    }

    /** Returns true if current net is assigned to any primary output. */
    public boolean assignedToPI() {
        for (Net net: assignedTo)
            if (net.isPI())
                return true;
        return false;
    }

    /** Returns true if current net is assigned to any primary output. */
    public boolean assignedToPO() {
        for (Net net: assignedTo)
            if (net.isPO())
                return true;
        return false;
    }

    /** Returns list of nets the current net object is assigned to. */
    public List<Net> assignedTo() {
        return assignedTo;
    }
}
