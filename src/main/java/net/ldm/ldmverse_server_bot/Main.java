package net.ldm.ldmverse_server_bot;

import com.google.gson.GsonBuilder;
import net.ldm.ldmverse_server_bot.file.FileUtils;

import java.util.Objects;

public class Main {
    public static final String TOKEN = new GsonBuilder().create().fromJson("", String.class);

    public static void main(String[] args) {
        //System.out.println(FileUtils.readFile("classpath:bot.json"));
        //System.out.println(Main.class.getResourceAsStream("bot.json"));
        System.out.println(FileUtils.readFile("bot.json"));
    }
}