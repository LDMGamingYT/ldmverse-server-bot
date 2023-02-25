package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class SetLevelCommand extends Command {
    public SetLevelCommand() {
        super("set-level", "Sets the user's level", true, Permission.EMPTY_PERMISSIONS, new OptionData[]{
                new OptionData(OptionType.INTEGER, "level", "The new level", true)
        });
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply(String.format("Setting %s's level to %s", event.getUser().getName(), event.getOption("level"))).queue();
    }
}