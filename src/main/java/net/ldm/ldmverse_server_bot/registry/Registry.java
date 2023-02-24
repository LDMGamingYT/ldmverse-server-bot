package net.ldm.ldmverse_server_bot.registry;

import java.util.ArrayList;
import java.util.HashMap;

public class Registry {
    public static final HashMap<Registries, ArrayList<RegistryObject>> REGISTRY_MAP = new HashMap<>();

    public static RegistryObject register(Registries registry, RegistryObject item) {
        return item.register(registry);
    }
}
