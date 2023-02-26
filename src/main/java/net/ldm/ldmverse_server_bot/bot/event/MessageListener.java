package net.ldm.ldmverse_server_bot.bot.event;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import net.ldm.ldmverse_server_bot.bot.stat.StatHandler;
import net.ldm.ldmverse_server_bot.bot.stat.UserStats;
import net.ldm.ldmverse_server_bot.core.exception.CalculationException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class MessageListener extends ListenerAdapter {
    private static final Logger LOG = LoggerContext.getContext().getLogger(MessageListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().equals(BotHandler.bot.getSelfUser())) return;

        UserStats stats = StatHandler.getUserStatsInServer(event.getGuild().getId(), event.getAuthor().getId());
        long oldLevel = stats.getLevel();
        stats.incrementSentMessages().incrementXp(12.5f, 25f);

        try {
            stats.setLevel(StatHandler.calculateLevelForXp(stats.getXp()));
        } catch (CalculationException e) {
            LOG.error("Calculation exception while calculating level from xp", e);
        }

        if (stats.getLevel() > oldLevel)
            event.getMessage().reply("You leveled up! You are now level " + stats.getLevel()).queue();

        StatHandler.setUserStatsInServer(event.getGuild().getId(), event.getAuthor().getId(), stats);
    }
}