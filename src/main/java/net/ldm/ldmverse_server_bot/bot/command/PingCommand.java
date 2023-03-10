package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", "Ping? Pong!");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Pong!").queue();
    }
}