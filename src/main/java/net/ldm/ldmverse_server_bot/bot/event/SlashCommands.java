package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.command.Command;
import net.ldm.ldmverse_server_bot.registry.Registries;
import net.ldm.ldmverse_server_bot.registry.Registry;
import net.ldm.ldmverse_server_bot.registry.RegistryObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.jetbrains.annotations.NotNull;

public class SlashCommands extends ListenerAdapter {
    private static final Logger LOG = LoggerContext.getContext().getLogger(SlashCommands.class);

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (Registry.REGISTRY_MAP.isEmpty() || Registry.REGISTRY_MAP.get(Registries.COMMAND).isEmpty()) {
            LOG.warn("Command registry is empty, cannot run/find commands");
            return;
        }
        for (RegistryObject object: Registry.REGISTRY_MAP.get(Registries.COMMAND)) {
            if (object instanceof Command command)
                if (event.getName().equals(command.getCommand()))
                    command.execute(event);
        }

        /*switch (event.getName()) {
            case "ping" -> new PingCommand().execute(event);
            // TODO: 2023-02-23 Add other commands
        }*/
    }
}