package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.BotHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class MessageListener extends ListenerAdapter {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotHandler.class);
    private static final String FORUM_ID = "1078078469377884220";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!(event.getChannel() instanceof ThreadChannel) || !event.getChannel().asThreadChannel().getParentChannel().getId().equals(FORUM_ID))
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
