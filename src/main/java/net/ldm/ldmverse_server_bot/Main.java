package net.ldm.ldmverse_server_bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.ldm.ldmverse_server_bot.file.FileUtils;
import net.ldm.ldmverse_server_bot.json.BotConfig;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    public static BotConfig BOT_CONFIG;

    public static void main(String[] args) {
        try {
            BOT_CONFIG = FileUtils.readJson("bot.json", BotConfig.class);
        } catch (Exception e) {
            if (!genConfig()) {
                System.out.println("Something went wrong.");
                System.exit(-1);
            }
            System.exit(0);
        }

        LoggerFactory.getLogger("main/bot").info("asd");

        //JDA bot = startBot();
    }

    private static JDA startBot() {
        return JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching("Java > Python"))
                .build();
    }

    private static boolean genConfig() {
        Scanner in = new Scanner(System.in);
        System.out.println("It seems you don't have your config setup. Let's do that!");
        System.out.print("Bot token? ");
        String token = in.nextLine();

        return FileUtils.writeJson("bot.json", new BotConfig(token));
    }
}