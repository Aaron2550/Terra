package com.dfsek.terra.api.event.events.world.generation;

import com.dfsek.terra.api.config.ConfigPack;
import com.dfsek.terra.api.event.events.PackEvent;
import com.dfsek.terra.api.entity.Entity;
import com.dfsek.terra.api.vector.Location;
import com.dfsek.terra.api.vector.Vector3;

/**
 * Called when an entity is spawned.
 */
public class EntitySpawnEvent implements PackEvent {
    private final ConfigPack pack;
    private final Entity entity;

    public EntitySpawnEvent(ConfigPack pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
    }

    @Override
    public ConfigPack getPack() {
        return pack;
    }

    /**
     * Get the entity that triggered the event.
     *
     * @return The entity.
     */
    public Entity getEntity() {
        return entity;
    }
}
