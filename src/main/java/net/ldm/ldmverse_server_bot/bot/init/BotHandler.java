package net.ldm.ldmverse_server_bot.bot.init;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
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

    private static JDA create() {
        LOG.info("Starting bot!");
        return JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("in development!"))
                .setLargeThreshold(50)
                .addEventListeners(new MessageListener(), new SlashCommands())
                .build();
    }

    private static void registerCommands(JDA bot) {
        bot.updateCommands().addCommands(
                Commands.slash("ping", "Ping? Pong!"),
                Commands.slash("suggestion", "Set the status of a suggestion.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .setGuildOnly(true)
                        .addOptions(new OptionData(OptionType.STRING, "status", "The status of the suggestion", true)
                                .addChoice("approve", "approve")
                                .addChoice("deny", "deny")
                                .addChoice("consider", "consider")
                                .addChoice("implement", "implement"))
                // TODO: 2023-02-23 Add commands from 'Sapphire' bot
        ).queue();
        LOG.info("Registered {} commands", bot.retrieveCommands().complete().size());
    }

    public static void start() {
        new BotRegistry().initialize();
        JDA bot = create();
        registerCommands(bot);
    }
}