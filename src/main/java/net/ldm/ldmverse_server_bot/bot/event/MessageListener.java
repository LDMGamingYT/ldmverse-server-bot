package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class MessageListener extends ListenerAdapter {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotHandler.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().equals(BotHandler.getBot().getSelfUser())) return;
        if (!(event.getChannel() instanceof ThreadChannel) || !event.getChannel().asThreadChannel().getParentChannel().getId().equals(BotHandler.FORUM_ID))
            return;
        LOG.info("Message sent in registered forum");
        ThreadChannel channel = event.getChannel().asThreadChannel();

        if (event.getMessage().equals(channel.getHistory().retrievePast(channel.getMessageCount()).complete().get(channel.getMessageCount() - 1))) {
            LOG.info("Message sent is the first (parent) message.");
        } else {
            LOG.info("Message sent is not the first (child) message.");
        }

        LOG.info("Message received: {}", event.getMessage().getContentDisplay());
    }
}