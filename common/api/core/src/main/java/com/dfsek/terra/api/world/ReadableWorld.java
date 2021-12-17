package com.dfsek.terra.api.world;

import com.dfsek.terra.api.block.entity.BlockEntity;
import com.dfsek.terra.api.block.state.BlockState;
import com.dfsek.terra.api.util.vector.Vector3;
import com.dfsek.terra.api.util.vector.integer.Vector3Int;


public interface ReadableWorld extends World {
    BlockState getBlockData(int x, int y, int z);
    
    default BlockState getBlockData(Vector3 position) {
        return getBlockData(position.getBlockX(), position.getBlockY(), position.getBlockZ());
    }
    
    default BlockState getBlockData(Vector3Int position) {
        return getBlockData(position.getX(), position.getY(), position.getZ());
    }
    
    BlockEntity getBlockEntity(int x, int y, int z);
    
    default BlockEntity getBlockEntity(Vector3 position) {
        return getBlockEntity(position.getBlockX(), position.getBlockY(), position.getBlockZ());
    }
    
    default BlockEntity getBlockEntity(Vector3Int position) {
        return getBlockEntity(position.getX(), position.getY(), position.getZ());
    }
}
