package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SetLevelCommand extends Command{
    public SetLevelCommand() {
        super("setlevel");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply(String.format("Setting %s's level to %s", event.getUser().getName(), event.getOption("level"))).queue();
    }
}