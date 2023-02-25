package net.ldm.ldmverse_server_bot.core.registry;

public interface RegistryObject {
    /**
     * This method should only be used by {@link Registry}.<br>
     * To register a {@link RegistryObject}, use {@link Registry#register}
     */
    default boolean register(Registries registryType) {
        return Registry.add(registryType, this);
    }
}