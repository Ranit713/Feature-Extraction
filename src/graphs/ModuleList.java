package graphs;

import java.util.HashMap;
import java.util.Map;

import components.SubModules;

// Singleton class storing set of sub modules.
class ModuleList {

    Map<String, SubModules> modules;
    private static ModuleList singleInstance = null;

    private ModuleList() {
        modules = new HashMap<>();
    }

    static ModuleList getInstance() {
        if (singleInstance == null) // to ensure only one instance is created
            singleInstance = new ModuleList();
        return singleInstance;
    }

    void add(SubModules module) {
        String name = module.getName();
        modules.putIfAbsent(name, module);
    }

    SubModules get(String name) {
        return modules.get(name);
    }
}
