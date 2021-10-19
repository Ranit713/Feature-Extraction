package graphs;

import java.util.List;

import components.Net;

/** Interface storing set of nets. */
public interface NetList {

    public void add(Net net);

    /**
     * Retrieves the net using its name. If not found(few Trojan nets are noy
     * defined in wire), create new net and return the object.
     */
    public Net getNet(String name);

    /** Returns all nets in the circuit. */
    public List<Net> getAllNets();

    /** Prints all nets' name. */
    public void showNetlist();

    public void assign(String name1, String name2);
}
