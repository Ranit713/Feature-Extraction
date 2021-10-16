package parsers.parser;

import components.Net;
import components.SubModule;
import graphs.Graph;

public class CircuitryToken {

    private Graph graph;

    public CircuitryToken() {
        graph = Graph.getInstance();
    }

    /**
     * Parse a circuitry token.
     */
    public void parse(String token) {
        if (token.startsWith("assign"))
            assignToken(token);
        else
            subModuleToken(token);
    }

    /**
     * Parses a token with assignment defintion.
     */
    private void assignToken(String token) {
        String assignment = token.split("assign\\s*")[1];
        assign(assignment);
    }

    /**
     * Actual assignment done here.
     */
    private void assign(String assignment) {
        String[] netNames = assignment.split("\\s*=\\s*");
        Net net = graph.getNet(netNames[1]);
        net.assignTo(netNames[0]);
    }

    /**
     * Splits circuit definition into module components.
     */
    private void subModuleToken(String circuitDefinition) {
        String[] circuitDefinitionTokens = circuitDefinition.split("\\s+", 3);
        String moduleName = circuitDefinitionTokens[1];
        String moduleDef = circuitDefinitionTokens[2];
        setModuleInfo(moduleName, moduleDef.substring(1, moduleDef.length() - 1).trim());
    }

    /**
     * Extract inputs and outputs of module from its definition and stores in graph
     */
    private void setModuleInfo(String moduleName, String moduleDef) {

        // object for the current module
        SubModule module = new SubModule(moduleName);

        String[] inout = moduleDef.split("\\s*,\\s*"); // split module defintion into further tokens

        for (int i = 0; i < inout.length; i++) {

            /*
             * Stores name of the port in current token. Name will later be used to
             * determine whether port is input or output port of the module.
             */
            String port = inout[i].substring(1, inout[i].indexOf("("));

            // Name of net connected to port previously retrieved.
            String netName = inout[i].substring(inout[i].indexOf("(") + 1, inout[i].indexOf(")")).trim();

            // For non-empty names only.
            if (!netName.isEmpty()) {
                Net net = graph.getNet(netName);
                if (port.equals("Q") || port.equals("QN")) { // output ports
                    module.addOutput(netName);
                    net.setInput(module);
                } else { // input ports
                    module.addInput(netName);
                    net.addOutputs(module);
                }
            }
        }
        graph.add(module);
    }
}
