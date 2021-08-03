package com.dfsek.terra.api.properties;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private final Map<Class<? extends Properties>, Properties> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Properties> T get(Class<T> clazz) {
        return (T) map.computeIfAbsent(clazz, k -> {
            throw new IllegalArgumentException("No properties registered for class " + clazz.getCanonicalName());
        });
    }

    public Context put(Properties properties) {
        if(map.containsKey(properties.getClass()))
            throw new IllegalArgumentException("Property for class " + properties.getClass().getCanonicalName() + " already registered.");
        map.put(properties.getClass(), properties);
        return this;
    }
}
