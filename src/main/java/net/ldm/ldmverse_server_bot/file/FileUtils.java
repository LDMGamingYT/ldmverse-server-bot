package net.ldm.ldmverse_server_bot.file;

import net.ldm.ldmverse_server_bot.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileUtils {
    public static String readFileD(String path) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    public static String readFile(String path) {
        try (InputStream inputStream = Main.class.getResourceAsStream(path)) {
            if (inputStream != null) {
                return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File not found.";
    }
}