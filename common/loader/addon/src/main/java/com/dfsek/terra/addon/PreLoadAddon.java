package com.dfsek.terra.addon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dfsek.terra.addon.exception.AddonLoadException;
import com.dfsek.terra.addon.exception.CircularDependencyException;
import com.dfsek.terra.addon.exception.DependencyMissingException;
import com.dfsek.terra.api.addon.TerraAddon;
import com.dfsek.terra.api.addon.annotations.Addon;
import com.dfsek.terra.api.addon.annotations.Depends;


public class PreLoadAddon {
    public static final String[] ZERO_LENGTH_STRING_ARRAY = { }; // Don't allocate more than once
    
    private final List<PreLoadAddon> depends = new ArrayList<>();
    private final Class<? extends TerraAddon> addonClass;
    private final String id;
    private final String[] dependencies;
    private final File file;
    
    public PreLoadAddon(Class<? extends TerraAddon> addonClass, File file) {
        this.addonClass = addonClass;
        this.id = addonClass.getAnnotation(Addon.class).value();
        this.file = file;
        Depends depends = addonClass.getAnnotation(Depends.class);
        this.dependencies = depends == null ? ZERO_LENGTH_STRING_ARRAY : depends.value();
    }
    
    public void rebuildDependencies(AddonPool pool, PreLoadAddon origin, boolean levelG1) throws AddonLoadException {
        if(this.equals(origin) && !levelG1)
            throw new CircularDependencyException(String.format("Detected circular dependency in addon \"%s\", dependencies: %s",
                                                                id, Arrays.toString(dependencies)));
        
        for(String dependency : dependencies) {
            PreLoadAddon preLoadAddon = pool.get(dependency);
            if(preLoadAddon == null)
                throw new DependencyMissingException(String.format("Dependency %s was not found. Please install %s to use %s.",
                                                                   dependency, dependency, id));
            depends.add(preLoadAddon);
            preLoadAddon.rebuildDependencies(pool, origin, false);
        }
    }
    
    public List<PreLoadAddon> getDepends() {
        return depends;
    }
    
    public String getId() {
        return id;
    }
    
    public Class<? extends TerraAddon> getAddonClass() {
        return addonClass;
    }
    
    public File getFile() {
        return file;
    }
}
