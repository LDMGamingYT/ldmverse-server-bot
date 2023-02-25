package net.ldm.ldmverse_server_bot.bot.event;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.ldm.ldmverse_server_bot.core.resource.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LevelHandler {
    private static final String FILE_NAME = "levels.json";
    private static final Logger LOG = LoggerContext.getContext().getLogger(LevelHandler.class);

    public static UserStats getStatsOf(String userId) {
        Map<String, UserStats> levels;
        try {
            levels = new GsonBuilder().create().fromJson(FileUtils.read(FILE_NAME), new TypeToken<>() {
            });
            if (levels == null) return null;
        } catch (FileNotFoundException e) {
            LOG.error("Something went wrong while retrieving " + FILE_NAME, e);
            throw new RuntimeException(e);
        }

        if (levels.get(userId) == null) return null;
        return levels.get(userId);
    }

    public static void setLevelOf(String userId, long level) {
        Map<String, UserStats> levels;
        try {
            levels = new GsonBuilder().create().fromJson(FileUtils.read(FILE_NAME), new TypeToken<>() {
            });
        } catch (FileNotFoundException e) {
            LOG.error("Something went wrong while retrieving " + FILE_NAME, e);
            throw new RuntimeException(e);
        }

        if (levels == null)
            levels = new HashMap<>();

        levels.put(userId, Objects.requireNonNull(getStatsOf(userId)).setLevel(level));

        FileUtils.saveJson(FILE_NAME, levels);
    }

    public static void incrementLevelOf(String userId) {
        setLevelOf(userId, Objects.requireNonNull(getStatsOf(userId)).level + 1);
    }

    static final class UserStats {
        private long level;
        private final long messagesSent;

        UserStats(long level, long messagesSent) {
            this.level = level;
            this.messagesSent = messagesSent;
        }

        public UserStats setLevel(long level) {
            this.level = level;
            return this;
        }

        @Override
        public String toString() {
            return "UserStats[" +
                    "level=" + level + ", " +
                    "messagesSent=" + messagesSent + ']';
        }
    }
}