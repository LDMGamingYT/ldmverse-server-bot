package net.ldm.ldmverse_server_bot.registry;

import java.util.ArrayList;
import java.util.Objects;

public interface RegistryObject {
    default RegistryObject register(Registries registryType) {
        if (!Registry.REGISTRY_MAP.containsKey(registryType))
            Objects.requireNonNull(Registry.REGISTRY_MAP.put(registryType, new ArrayList<>())).add(this);
        else
            Registry.REGISTRY_MAP.get(registryType).add(this);
        return this;
    }
}
