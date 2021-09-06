package com.dfsek.terra.addons.generation.feature;

import com.dfsek.terra.addons.generation.feature.config.BiomeFeatures;
import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.profiler.ProfileFrame;
import com.dfsek.terra.api.structure.rotation.Rotation;
import com.dfsek.terra.api.util.PopulationUtil;
import com.dfsek.terra.api.vector.Vector3;
import com.dfsek.terra.api.world.Chunk;
import com.dfsek.terra.api.world.World;
import com.dfsek.terra.api.world.generator.GenerationStage;


public class FeatureGenerationStage implements GenerationStage {
    private final TerraPlugin main;
    
    public FeatureGenerationStage(TerraPlugin main) {
        this.main = main;
    }
    
    @Override
    @SuppressWarnings("try")
    public void populate(World world, Chunk chunk) {
        try(ProfileFrame ignore = main.getProfiler().profile("feature")) {
            int cx = chunk.getX() << 4;
            int cz = chunk.getZ() << 4;
            long seed = world.getSeed();
            for(int x = 0; x < 16; x++) {
                for(int z = 0; z < 16; z++) {
                    int tx = cx + x;
                    int tz = cz + z;
                    ColumnImpl column = new ColumnImpl(tx, tz, world);
                    world.getBiomeProvider().getBiome(tx, tz, seed).getContext().get(BiomeFeatures.class).getFeatures().forEach(feature -> {
                        if(feature.getDistributor().matches(tx, tz, seed)) {
                            feature.getLocator()
                                   .getSuitableCoordinates(column)
                                   .forEach(y ->
                                                    feature.getStructure(world, tx, y, tz)
                                                           .generate(new Vector3(tx, y, tz), world, PopulationUtil.getRandom(chunk),
                                                                     Rotation.NONE)
                                           );
                        }
                    });
                }
            }
        }
    }
}