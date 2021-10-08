package graphs;

import components.SubModules;

// Singleton class storing set of sub-modules.
public interface ModuleList {

    public void add(SubModules module);

    public SubModules getModule(String name);
}
