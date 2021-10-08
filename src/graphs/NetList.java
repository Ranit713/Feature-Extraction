package graphs;

import java.util.Set;

import components.Net;

/* Singleton class storing set of nets. */
public interface NetList {

    public void add(Net net);

    /*
     * Retrieves the net using its name. If not found(few Trojan nets are noy
     * defined in wire), create new net and return the object.
     */
    public Net getNet(String name);

    public Set<String> getAllNets();

    /* Prints all nets' name */
    public void showNetlist();

    public void assign(String name1, String name2);
}
