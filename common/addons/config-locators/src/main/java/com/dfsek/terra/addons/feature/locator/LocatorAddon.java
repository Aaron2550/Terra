package com.dfsek.terra.addons.feature.locator;

import com.dfsek.tectonic.loading.object.ObjectTemplate;

import java.util.function.Supplier;

import com.dfsek.terra.addons.feature.locator.config.AndLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.NoiseLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.OrLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.PatternLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.RandomLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.SurfaceLocatorTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.AirMatchPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.AndPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.BlockSetMatchPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.NotPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.OrPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.SingleBlockMatchPatternTemplate;
import com.dfsek.terra.addons.feature.locator.config.pattern.SolidMatchPatternTemplate;
import com.dfsek.terra.addons.feature.locator.patterns.Pattern;
import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.addon.TerraAddon;
import com.dfsek.terra.api.addon.annotations.Addon;
import com.dfsek.terra.api.addon.annotations.Author;
import com.dfsek.terra.api.addon.annotations.Version;
import com.dfsek.terra.api.event.events.config.pack.ConfigPackPreLoadEvent;
import com.dfsek.terra.api.event.functional.FunctionalEventHandler;
import com.dfsek.terra.api.injection.annotations.Inject;
import com.dfsek.terra.api.registry.CheckedRegistry;
import com.dfsek.terra.api.structure.feature.Locator;
import com.dfsek.terra.api.util.reflection.TypeKey;


@Addon("config-locators")
@Version("1.0.0")
@Author("Terra")
public class LocatorAddon extends TerraAddon {
    
    public static final TypeKey<Supplier<ObjectTemplate<Locator>>> LOCATOR_TOKEN = new TypeKey<>() {
    };
    public static final TypeKey<Supplier<ObjectTemplate<Pattern>>> PATTERN_TOKEN = new TypeKey<>() {
    };
    @Inject
    private TerraPlugin main;
    
    @Override
    public void initialize() {
        main.getEventManager()
            .getHandler(FunctionalEventHandler.class)
            .register(this, ConfigPackPreLoadEvent.class)
            .then(event -> {
                CheckedRegistry<Supplier<ObjectTemplate<Locator>>> locatorRegistry = event.getPack().getOrCreateRegistry(LOCATOR_TOKEN);
                locatorRegistry.register("SURFACE", () -> new SurfaceLocatorTemplate(main));
                locatorRegistry.register("RANDOM", RandomLocatorTemplate::new);
                locatorRegistry.register("PATTERN", PatternLocatorTemplate::new);
                locatorRegistry.register("NOISE", NoiseLocatorTemplate::new);
            
                locatorRegistry.register("AND", AndLocatorTemplate::new);
                locatorRegistry.register("OR", OrLocatorTemplate::new);
            })
            .then(event -> {
                CheckedRegistry<Supplier<ObjectTemplate<Pattern>>> patternRegistry = event.getPack().getOrCreateRegistry(PATTERN_TOKEN);
                patternRegistry.register("MATCH_AIR", AirMatchPatternTemplate::new);
                patternRegistry.register("MATCH_SOLID", SolidMatchPatternTemplate::new);
                patternRegistry.register("MATCH", SingleBlockMatchPatternTemplate::new);
                patternRegistry.register("MATCH_SET", BlockSetMatchPatternTemplate::new);
            
                patternRegistry.register("AND", AndPatternTemplate::new);
                patternRegistry.register("OR", OrPatternTemplate::new);
                patternRegistry.register("NOT", NotPatternTemplate::new);
            })
            .failThrough();
    }
}