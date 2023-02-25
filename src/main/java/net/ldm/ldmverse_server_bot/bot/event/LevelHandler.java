package net.ldm.ldmverse_server_bot.bot.event;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.ldm.ldmverse_server_bot.core.resource.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.FileNotFoundException;
import java.util.Map;

public class LevelHandler {
    private static final String FILE_NAME = "levels.json";
    private static final Logger LOG = LoggerContext.getContext().getLogger(LevelHandler.class);

    public static int getLevelOf(String userId) {
        Map<String, Integer> levels;
        try {
            levels = new GsonBuilder().create().fromJson(FileUtils.read(FILE_NAME), new TypeToken<>() {});
            if (levels == null) return -1;
        } catch (FileNotFoundException e) {
            LOG.error("Something went wrong while retrieving {}", FILE_NAME);
            throw new RuntimeException(e);
        }

        if (levels.get(userId) == null) return -1;
        return levels.get(userId);
    }
}