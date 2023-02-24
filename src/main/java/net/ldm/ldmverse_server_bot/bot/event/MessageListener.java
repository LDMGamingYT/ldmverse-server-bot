package net.ldm.ldmverse_server_bot.bot.event;

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
        event.getMessage().reply("you leveled up or something and the level is like "+LevelHandler.getLevelOf(event.getAuthor().getId())).queue();
    }
}