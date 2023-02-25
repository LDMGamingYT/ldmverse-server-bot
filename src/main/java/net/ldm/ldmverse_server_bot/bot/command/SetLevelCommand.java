package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.ldm.ldmverse_server_bot.bot.event.LevelHandler;

import java.util.Objects;

public class SetLevelCommand extends Command {
    public SetLevelCommand() {
        super("set-level", "Sets your own level", true, Permission.EMPTY_PERMISSIONS, new OptionData[]{
                new OptionData(OptionType.INTEGER, "level", "The new level", true)
        });
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        LevelHandler.setLevelOf(event.getUser().getId(), Objects.requireNonNull(event.getOption("level")).getAsInt());
        event.reply(String.format("Set %s's level to %s", event.getUser().getName(), Objects.requireNonNull(event.getOption("level")).getAsInt())).queue();
    }
}