package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().equals(BotHandler.bot.getSelfUser())) return;
        LevelHandler.incrementLevelOf(event.getAuthor().getId());
        event.getMessage().reply("you leveled up or something and the level is like "+LevelHandler.getLevelOf(event.getAuthor().getId())).queue();
    }
}