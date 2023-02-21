package net.ldm.ldmverse_server_bot.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class FileUtils {
    public static String readFile(URL path) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path.getFile()))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}