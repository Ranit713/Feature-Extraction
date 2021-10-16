package graphs;

import components.SubModules;

// Singleton class storing set of sub-modules.
public interface ModuleList {

    /** Add a new sub-module. */
    public void add(SubModules module);

    /** Returns a sub-module using it's corresponding name. */
    public SubModules getModule(String name);
}
