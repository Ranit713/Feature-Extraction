package components;

import java.util.ArrayList;
import java.util.List;

public class SubModule {

    private String moduleName;
    private List<String> inputs;
    private List<String> outputs;

    public SubModule(String name) {
        moduleName = name;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    /** Adds names of input nets to the module. */
    public void addInput(String netName) {
        inputs.add(netName);
    }

    /** Adds names of output nets from the module. */
    public void addOutput(String name) {
        outputs.add(name);
    }

    /** Returns name of current module. */
    public String getName() {
        return moduleName;
    }

    /** Returns names of input nets to the module. */
    public List<String> getInputs() {
        return inputs;
    }

    /** Returns names of output nets from the module. */
    public List<String> getOutputs() {
        return outputs;
    }

    /* Returns fan in value of current module. */
    public int fanIn() {
        return inputs.size();
    }

    /* Returns fan out value of current module. */
    public int fanOut() {
        return outputs.size();
    }
}
