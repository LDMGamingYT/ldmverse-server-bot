package net.ldm.ldmverse_server_bot.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.ldm.ldmverse_server_bot.bot.event.MessageListener;
import net.ldm.ldmverse_server_bot.bot.event.SlashCommands;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import static net.ldm.ldmverse_server_bot.Main.BOT_CONFIG;

public class BotHandler {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotHandler.class);

    private static JDA create() {
        LOG.info("Starting bot!");
        return JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("in development!"))
                .setLargeThreshold(50)
                .addEventListeners(new MessageListener())
                .build();
    }

    // TODO: 2023-02-23 This doesn't work. Fix it. (it seems to be a registration issue, good luck dumbass) 
    private static void registerCommands(JDA bot) {
        bot.updateCommands().addCommands(
                Commands.slash("ping", "Ping? Pong!"),
                Commands.slash("suggestion", "Set the status of a suggestion.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .setGuildOnly(true)
                        //.addOption() // TODO: 2023-02-23 Use the resources below to finish this 💀
                        /* https://github.com/DV8FromTheWorld/JDA
                         * https://jda.wiki/using-jda/interactions/#slash-commands
                         */
                // TODO: 2023-02-23 Add commands from 'Sapphire' bot 
        ).queue();
        bot.addEventListener(new SlashCommands());
        LOG.info("Registered {} commands", bot.retrieveCommands().complete().size());
    }

    public static void start() {
        JDA bot = create();
        registerCommands(bot);
        bot
    }
}