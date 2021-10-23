package components;

import java.util.ArrayList;
import java.util.List;

public class SubModule {

    private String name;
    private String type;
    private List<Net> inputs;
    private List<Net> outputs;

    public SubModule(String moduleType, String moduleName) {
        name = moduleName;
        type = moduleType;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    /** Adds input nets to the module. */
    public void addInput(Net net) {
        inputs.add(net);
    }

    /** Adds output nets from the module. */
    public void addOutput(Net net) {
        outputs.add(net);
    }

    /** Returns name of current module. */
    public String getName() {
        return name;
    }

    /** Returns true if module is a Flip-Flop. */
    public boolean isFlipFlop() {
        return type.equals("FF");
    }

    /** Returns names of input nets to the module. */
    public List<Net> getInputs() {
        return inputs;
    }

    /** Returns names of output nets from the module. */
    public List<Net> getOutputs() {
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
