package net.ldm.ldmverse_server_bot.core.registry;

import java.util.ArrayList;
import java.util.HashMap;

public class Registry {
    private static final HashMap<Registries, ArrayList<RegistryObject>> REGISTRY_MAP = new HashMap<>();

    public static boolean register(Registries registry, RegistryObject item) {
        return item.register(registry);
    }

    public static boolean isTypeEmpty(Registries registryType) {
        return REGISTRY_MAP.isEmpty() || !REGISTRY_MAP.containsKey(registryType) || REGISTRY_MAP.get(registryType).isEmpty();
    }

    public static boolean add(Registries registryType, RegistryObject item) {
        REGISTRY_MAP.putIfAbsent(registryType, new ArrayList<>());
        return REGISTRY_MAP.get(registryType).add(item);
    }

    public static ArrayList<RegistryObject> getRegisteredFrom(Registries type) {
        return REGISTRY_MAP.get(type);
    }
}