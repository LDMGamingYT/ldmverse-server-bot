package net.ldm.ldmverse_server_bot.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import net.ldm.ldmverse_server_bot.resource.RemoteResourceGetter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Objects;

public class SuggestionCommand extends Command {
    private static final Logger LOG = LoggerContext.getContext().getLogger(SuggestionCommand.class);
    public SuggestionCommand() {
        super("suggestion");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String status = Objects.requireNonNull(event.getOption("status")).getAsString();
        if (!event.getChannelType().isThread() || !event.getChannel().asThreadChannel().getParentChannel().getId().equals(BotHandler.FORUM_ID)) {
            event.reply(":warning: You cannot use this command here!").setEphemeral(true).queue();
            return;
        }

        switch (status) {
            case "approve" -> sendSimpleEmbed(event, "This suggestion has been approved!",
                    "Thanks for sharing your opinion! Your suggestion has been queued up for development!",
                    0x0099e5, RemoteResourceGetter.getPathToResource("images/suggestion_approved.png"),
                    "Approved by @"+event.getUser().getName(), event.getUser().getAvatarUrl(),
                    "Suggestion approved as of");
            case "deny" -> sendSimpleEmbed(event, "This suggestion has been denied.",
                    "Thanks for sharing your opinion! Unfortunately, this suggestion has not been approved, and will not be added.",
                    0xCE1842, RemoteResourceGetter.getPathToResource("images/suggestion_denied.png"),
                    "Denied by @"+event.getUser().getName(), event.getUser().getAvatarUrl(),
                    "Suggestion denied as of");
            case "implement" -> sendSimpleEmbed(event, "This suggestion has been implemented!",
            "Thanks for sharing your opinion! Your suggestion has made it to the finish line, and will be released in the next update!",
                    0x78b354, RemoteResourceGetter.getPathToResource("images/suggestion_implemented.png"),
                    "Marked as implemented by @"+event.getUser().getName(), event.getUser().getAvatarUrl(),
                    "Suggestion implemented as of");
            case "consider" -> sendSimpleEmbed(event, "This suggestion is being considered!",
                    "Thanks for sharing your opinion! Your suggestion is being considered.",
                    0xFFCC4B, RemoteResourceGetter.getPathToResource("images/suggestion_considered.png"),
                    "Marked as considered by @"+event.getUser().getName(), event.getUser().getAvatarUrl(),
                    "Suggestion marked as considered as of");
            default -> {
                LOG.error("\"status\" argument ({}) is invalid! Executed by {}", status, event.getUser().getAsTag());
                event.reply(":warning: Invalid status!").setEphemeral(true).queue();
            }
        }
    }
}