package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.ldm.ldmverse_server_bot.registry.RegistryObject;

import java.time.OffsetDateTime;

public abstract class Command implements RegistryObject {
    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(SlashCommandInteractionEvent event);

    public String getCommand() {
        return command;
    }

    protected void sendSimpleEmbed(SlashCommandInteractionEvent event, String title, String description, int color,
                                   String thumbnailUrl, String author, String authorIconUrl, String footer) {
        event.replyEmbeds(new MessageEmbed(null, title, description, EmbedType.RICH, OffsetDateTime.now(), color,
                new MessageEmbed.Thumbnail(thumbnailUrl, null, 100000, 100000), null,
                new MessageEmbed.AuthorInfo(author, null, authorIconUrl, null), null,
                new MessageEmbed.Footer(footer, null, null), null, null)).queue();
    }
}