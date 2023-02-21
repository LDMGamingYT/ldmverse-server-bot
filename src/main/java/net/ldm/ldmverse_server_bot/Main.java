package net.ldm.ldmverse_server_bot;

import com.google.gson.GsonBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.ldm.ldmverse_server_bot.file.FileUtils;
import net.ldm.ldmverse_server_bot.json.BotConfig;

public class Main {
    public static final BotConfig BOT_CONFIG = new GsonBuilder().create().fromJson(FileUtils.readFile("bot.json"), BotConfig.class);

    public static void main(String[] args) {
        System.out.println(BOT_CONFIG.token);
        JDA bot = JDABuilder.createDefault(BOT_CONFIG.token)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching("Java > Python"))
                .build();
        System.out.println(bot.getTextChannels());
    }
}