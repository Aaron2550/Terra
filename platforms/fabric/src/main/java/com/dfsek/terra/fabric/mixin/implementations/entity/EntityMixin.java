/*
 * This file is part of Terra.
 *
 * Terra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Terra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Terra.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.dfsek.terra.fabric.mixin.implementations.entity;

import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

import com.dfsek.terra.api.util.vector.Vector3;
import com.dfsek.terra.api.world.access.ServerWorld;
import com.dfsek.terra.fabric.util.FabricAdapter;


@Mixin(Entity.class)
@Implements(@Interface(iface = com.dfsek.terra.api.entity.Entity.class, prefix = "terra$", remap = Interface.Remap.NONE))
public abstract class EntityMixin {
    @Shadow
    public net.minecraft.world.World world;
    
    @Shadow
    private BlockPos blockPos;
    
    @Shadow
    public abstract void teleport(double destX, double destY, double destZ);
    
    @Shadow
    public abstract void sendSystemMessage(Text message, UUID senderUuid);
    
    @Intrinsic
    public Object terra$getHandle() {
        return this;
    }
    
    public Vector3 terra$position() {
        return FabricAdapter.adapt(blockPos);
    }
    
    public void terra$position(Vector3 location) {
        teleport(location.getX(), location.getY(), location.getZ());
    }
    
    public ServerWorld terra$world() {
        return (ServerWorld) world;
    }
    
    public void terra$sendMessage(String message) {
        sendSystemMessage(new LiteralText(message), UUID.randomUUID()); // TODO: look into how this actually works and make it less jank
    }
}
