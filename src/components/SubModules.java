package components;

import java.util.ArrayList;
import java.util.List;

public class SubModules {

    private String moduleName;
    private List<String> inputs;
    private List<String> outputs;

    public SubModules(String name) {
        moduleName = name;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    public void addInput(String name) {
        inputs.add(name);
    }

    public void addOutput(String name) {
        outputs.add(name);
    }

    public String getName() {
        return moduleName;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public int fanIn() {
        return inputs.size();
    }

    public int fanOut() {
        return outputs.size();
    }
}
