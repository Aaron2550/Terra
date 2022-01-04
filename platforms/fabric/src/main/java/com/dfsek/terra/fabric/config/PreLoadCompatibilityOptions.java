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

package com.dfsek.terra.fabric.config;

import com.dfsek.tectonic.api.config.template.ConfigTemplate;
import com.dfsek.tectonic.api.config.template.annotations.Default;
import com.dfsek.tectonic.api.config.template.annotations.Value;

import com.dfsek.terra.api.properties.Properties;

import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("FieldMayBeFinal")
public class PreLoadCompatibilityOptions implements ConfigTemplate, Properties {
    @Value("fabric.use-vanilla-biomes")
    @Default
    private boolean vanillaBiomes = false;
    
    @Value("fabric.beard.enable")
    @Default
    private boolean beard = true;
    
    @Value("fabric.beard.threshold")
    @Default
    private double beardThreshold = 0.5;
    
    public boolean useVanillaBiomes() {
        return vanillaBiomes;
    }
    
    public boolean isBeard() {
        return beard;
    }
    
    public double getBeardThreshold() {
        return beardThreshold;
    }
}
