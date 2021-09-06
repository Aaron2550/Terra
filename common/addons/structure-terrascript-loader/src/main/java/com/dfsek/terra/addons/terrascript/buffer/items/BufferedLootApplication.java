package com.dfsek.terra.addons.terrascript.buffer.items;

import java.util.Random;

import com.dfsek.terra.addons.terrascript.script.StructureScript;
import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.block.entity.BlockEntity;
import com.dfsek.terra.api.block.entity.Container;
import com.dfsek.terra.api.event.events.world.generation.LootPopulateEvent;
import com.dfsek.terra.api.structure.LootTable;
import com.dfsek.terra.api.structure.buffer.BufferedItem;
import com.dfsek.terra.api.vector.Vector3;
import com.dfsek.terra.api.world.World;


public class BufferedLootApplication implements BufferedItem {
    private final LootTable table;
    private final TerraPlugin main;
    private final StructureScript structure;
    
    public BufferedLootApplication(LootTable table, TerraPlugin main, StructureScript structure) {
        this.table = table;
        this.main = main;
        this.structure = structure;
    }
    
    @Override
    public void paste(Vector3 origin, World world) {
        try {
            BlockEntity data = world.getBlockState(origin);
            if(!(data instanceof Container)) {
                main.logger().severe("Failed to place loot at " + origin + "; block " + data + " is not container.");
                return;
            }
            Container container = (Container) data;
            
            LootPopulateEvent event = new LootPopulateEvent(container, table, world.getConfig().getPack(), structure);
            main.getEventManager().callEvent(event);
            if(event.isCancelled()) return;
            
            event.getTable().fillInventory(container.getInventory(), new Random(origin.hashCode()));
            data.update(false);
        } catch(Exception e) {
            main.logger().warning("Could not apply loot at " + origin + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}