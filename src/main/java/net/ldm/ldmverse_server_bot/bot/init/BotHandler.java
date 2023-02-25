package net.ldm.ldmverse_server_bot.bot.init;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.ldm.ldmverse_server_bot.bot.event.MessageListener;
import net.ldm.ldmverse_server_bot.bot.event.SlashCommands;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import static net.ldm.ldmverse_server_bot.Main.BOT_CONFIG;

public class BotHandler {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotHandler.class);
    public static final String FORUM_ID = "1078078469377884220";
    public static JDA bot;

    private static JDA create() {
        LOG.info("Creating new bot session");
        return JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("in development!"))
                .setLargeThreshold(50)
                .addEventListeners(new MessageListener(), new SlashCommands())
                .build();
    }

    public static void start() {
        LOG.info("Starting bot, preparing to initialize registry");
        bot = create();
        new BotRegistry().initialize();
        //registerCommands(bot);
    }
}