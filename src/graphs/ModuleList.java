package graphs;

import components.SubModule;

// Singleton class storing set of sub-modules.
public interface ModuleList {

    /** Add a new sub-module. */
    public void add(SubModule module);

    /** Returns a sub-module using its corresponding name. */
    public SubModule getModule(String name);
}
