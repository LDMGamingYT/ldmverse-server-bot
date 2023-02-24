package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.ldm.ldmverse_server_bot.registry.RegistryObject;

public abstract class Command implements RegistryObject {
    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(SlashCommandInteractionEvent event);

    public String getCommand() {
        return command;
    }
}
