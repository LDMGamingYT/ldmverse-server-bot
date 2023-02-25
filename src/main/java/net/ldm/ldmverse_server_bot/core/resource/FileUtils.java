package net.ldm.ldmverse_server_bot.core.resource;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    private static final Logger LOG = LoggerContext.getContext().getLogger(FileUtils.class);

    public static String read(String path) throws FileNotFoundException {
        LOG.trace("Reading file: {}", path);
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            LOG.error("Failed to read "+path, e);
            throw new RuntimeException(e);
        }
        if (content.isEmpty()) throw new FileNotFoundException(path);
        return content.toString();
    }

    public static <T> T readJson(String path, Class<T> type) throws FileNotFoundException, JsonSyntaxException {
        return new GsonBuilder().create().fromJson(read(path), type);
    }

    public static boolean create(String fileName) throws IOException {
        File file = new File(fileName);
        LOG.trace("File created: {}", file.getName());
        return file.createNewFile();
    }

    public static boolean write(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
            LOG.trace("Writen to file: {}", fileName);
            return true;
        } catch (IOException e) {
            LOG.error("An unknown error occurred while writing to " + fileName, e);
            return false;
        }
    }

    public static boolean saveJson(String fileName, Object content) {
        try {
            if (!create(fileName)) System.out.println();
            return write(fileName, new GsonBuilder().create().toJson(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}