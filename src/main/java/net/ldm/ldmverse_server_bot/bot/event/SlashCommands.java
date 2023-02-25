package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.command.Command;
import net.ldm.ldmverse_server_bot.core.registry.Registries;
import net.ldm.ldmverse_server_bot.core.registry.Registry;
import net.ldm.ldmverse_server_bot.core.registry.RegistryObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.jetbrains.annotations.NotNull;

public class SlashCommands extends ListenerAdapter {
    private static final Logger LOG = LoggerContext.getContext().getLogger(SlashCommands.class);

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (Registry.isTypeEmpty(Registries.COMMAND)) {
            LOG.warn("Command registry is empty, cannot run/find commands");
            return;
        }
        for (RegistryObject object: Registry.getRegisteredFrom(Registries.COMMAND)) {
            if (object instanceof Command command)
                if (event.getName().equals(command.getCommand()))
                    command.execute(event);
        }
    }
}