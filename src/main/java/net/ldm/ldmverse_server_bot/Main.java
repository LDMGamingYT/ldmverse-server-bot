package net.ldm.ldmverse_server_bot;

import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import net.ldm.ldmverse_server_bot.bot.init.BotRegistry;
import net.ldm.ldmverse_server_bot.core.crash.LoggerExceptionHandler;
import net.ldm.ldmverse_server_bot.core.resource.FileUtils;
import net.ldm.ldmverse_server_bot.json.BotConfig;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Scanner;

public class Main {
    public static BotConfig BOT_CONFIG;
    private static final Scanner IN = new Scanner(System.in);
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);
    public static final String RUN_DIRECTORY = "run\\";
    private static final String BOT_CONFIG_PATH = RUN_DIRECTORY + "bot.json";
    public static final BotRegistry REGISTRY = new BotRegistry();

    public static void main(String[] args) {
        LOG.info("Starting application");
        Thread.setDefaultUncaughtExceptionHandler(new LoggerExceptionHandler());

        try {
            BOT_CONFIG = FileUtils.readJson(BOT_CONFIG_PATH, BotConfig.class);
        } catch (Exception e) {
            System.out.println("It seems you don't have your config setup. Let's do that!");
            System.out.print("Bot token? ");
            String token = IN.nextLine();

            BOT_CONFIG = new BotConfig(token);

            if (!FileUtils.saveJson(BOT_CONFIG_PATH, BOT_CONFIG)) {
                LOG.fatal("Something went wrong while writing the bot config to bot.json");
                System.exit(-1);
            }
            LOG.info("Bot config created, file saved");
        }

        if (BOT_CONFIG.token == null || BOT_CONFIG.token.isEmpty()) {
            LOG.error("Bot token is missing or corrupt.");
            System.out.println("Your bot token is missing or corrupt. Please paste it below.");
            BOT_CONFIG.token = IN.nextLine();
            FileUtils.saveJson(BOT_CONFIG_PATH, BOT_CONFIG);
        }

        BotHandler.start();
    }
}