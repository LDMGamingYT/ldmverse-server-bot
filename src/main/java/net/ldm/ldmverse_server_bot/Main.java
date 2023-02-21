package net.ldm.ldmverse_server_bot;

import com.google.gson.GsonBuilder;
import net.ldm.ldmverse_server_bot.file.FileUtils;

public class Main {
    public static final String TOKEN = new GsonBuilder().create().fromJson("", String.class);

    public static void main(String[] args) {
        System.out.println(FileUtils.readFile(Main.class.getResource("bot.json")));
        //System.out.println(Main.class.getResourceAsStream("bot.json"));
    }
}