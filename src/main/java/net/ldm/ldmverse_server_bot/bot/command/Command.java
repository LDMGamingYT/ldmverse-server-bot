package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.ldm.ldmverse_server_bot.core.registry.Registries;
import net.ldm.ldmverse_server_bot.core.registry.RegistryObject;

import java.time.OffsetDateTime;

public abstract class Command implements RegistryObject {
    private final String command;
    private final String description;
    private final boolean guildOnly;
    private final Permission[] permissions;
    private final OptionData[] options;

    public Command(String command, String description, boolean guildOnly, Permission[] permissions, OptionData[] options) {
        this.command = command;
        this.description = description;
        this.guildOnly = guildOnly;
        this.permissions = permissions;
        this.options = options;
    }

    public Command(String command, String description, boolean guildOnly) {
        this(command, description, guildOnly, Permission.EMPTY_PERMISSIONS, new OptionData[0]);
    }

    public Command(String command, String description) {
        this(command, description, false, Permission.EMPTY_PERMISSIONS, new OptionData[0]);
    }

    public abstract void execute(SlashCommandInteractionEvent event);

    protected void sendSimpleEmbed(SlashCommandInteractionEvent event, String title, String description, int color,
                                   String thumbnailUrl, String author, String authorIconUrl, String footer) {
        event.replyEmbeds(new MessageEmbed(null, title, description, EmbedType.RICH, OffsetDateTime.now(), color,
                new MessageEmbed.Thumbnail(thumbnailUrl, null, 100000, 100000), null,
                new MessageEmbed.AuthorInfo(author, null, authorIconUrl, null), null,
                new MessageEmbed.Footer(footer, null, null), null, null)).queue();
    }

    @Override
    public boolean register(Registries registryType) {

        return RegistryObject.super.register(registryType);
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public OptionData[] getOptions() {
        return options;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    /*public record Option(OptionType type, String name, String description, boolean isRequired, net.dv8tion.jda.api.interactions.commands.Command.Choice... choices) {
    }*/
}