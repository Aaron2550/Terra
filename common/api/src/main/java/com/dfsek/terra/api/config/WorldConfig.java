package com.dfsek.terra.api.config;

import com.dfsek.terra.api.registry.Registry;
import com.dfsek.terra.api.world.TerraWorld;
import com.dfsek.terra.api.world.biome.generation.BiomeProvider;
import com.dfsek.terra.api.world.generator.SamplerCache;

import java.util.Map;

public interface WorldConfig {
    @SuppressWarnings("unchecked")
    <T> Registry<T> getRegistry(Class<T> clazz);

    TerraWorld getWorld();

    SamplerCache getSamplerCache();

    BiomeProvider getProvider();

    int elevationBlend();

    boolean disableTrees();

    boolean disableCarving();

    boolean disableOres();

    boolean disableFlora();

    boolean disableStructures();

    String getID();

    String getAuthor();

    String getVersion();

    Map<String, String> getLocatable();

    boolean isDisableSaplings();
}