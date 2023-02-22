package net.ldm.ldmverse_server_bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.ldm.ldmverse_server_bot.file.FileUtils;
import net.ldm.ldmverse_server_bot.json.BotConfig;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Scanner;

public class Main {
    public static BotConfig BOT_CONFIG;
    private static final Scanner IN = new Scanner(System.in);
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);

    public static void main(String[] args) {
        try {
            BOT_CONFIG = FileUtils.readJson("bot.json", BotConfig.class);
        } catch (Exception e) {
            System.out.println("It seems you don't have your config setup. Let's do that!");
            System.out.print("Bot token? ");
            String token = IN.nextLine();

            if (!FileUtils.writeJson("bot.json", new BotConfig(token))) {
                System.out.println("Something went wrong.");
                System.exit(-1);
            }
            System.exit(0);
        }

        if (BOT_CONFIG.token == null || BOT_CONFIG.token.isEmpty()) {
            System.out.println("Your bot token is missing or corrupt. Please paste it below.");
            BOT_CONFIG.token = IN.nextLine();
            FileUtils.writeJson("bot.json", BOT_CONFIG);
        }

        //JDA bot = startBot();
    }

    private static JDA startBot() {
        return JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching("Java > Python"))
                .build();
    }
}