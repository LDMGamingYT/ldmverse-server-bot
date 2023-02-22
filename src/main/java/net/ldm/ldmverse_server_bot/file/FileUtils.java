package net.ldm.ldmverse_server_bot.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.ldm.ldmverse_server_bot.json.BotConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    public static String read(String path) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (content.isEmpty()) throw new FileNotFoundException(path);
        return content.toString();
    }

    public static <T>T readJson(String path, Class<T> type) throws FileNotFoundException, JsonSyntaxException {
        return new GsonBuilder().create().fromJson(read(path), type);
    }

    public static boolean create(String fileName) {
        try {
            File file = new File(fileName);
            System.out.println("File created: " + file.getName());
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean write(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean writeJson(BotConfig content) {
        create("bot.json");
        write("bot.json", new GsonBuilder().create().toJson(content));
    }
}