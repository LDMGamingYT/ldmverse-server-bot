package net.ldm.ldmverse_server_bot.bot.stat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.ldm.ldmverse_server_bot.Main;
import net.ldm.ldmverse_server_bot.core.exception.CalculationException;
import net.ldm.ldmverse_server_bot.core.resource.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class StatHandler {
    private static final String DIRECTORY = Main.RUN_DIRECTORY + "stats\\";
    private static final Logger LOG = LoggerContext.getContext().getLogger(StatHandler.class);

    public static Map<String, UserStats> getDataFromServer(String serverId) {
        if (!new File(DIRECTORY + serverId + ".json").isFile())
            return new HashMap<>();

        try {
            return new Gson().fromJson(FileUtils.read(DIRECTORY + serverId + ".json"), new TypeToken<>() {
            });
        } catch (FileNotFoundException e) {
            LOG.error("An error occurred while reading server stats for server " + serverId, e);
            return new HashMap<>();
        }
    }

    public static void setDataOfServer(String serverId, Map<String, UserStats> data) {
        FileUtils.saveJson(DIRECTORY + serverId + ".json", data);
    }

    public static void setUserStatsInServer(String serverId, String userId, UserStats stats) {
        Map<String, UserStats> serverData = getDataFromServer(serverId);
        serverData.put(userId, stats);
        setDataOfServer(serverId, serverData);
    }

    public static UserStats getUserStatsInServer(String serverId, String userId) {
        if (getDataFromServer(serverId).get(userId) == null)
            return new UserStats(0, 0, 0);
        return getDataFromServer(serverId).get(userId);
    }

    public static long calculateXpForLevel(long level) {
        return 40 * (long) Math.pow(level, 2) + 23 * level + 42;
    }

    public static long calculateLevelForXp(float xp) throws CalculationException {
        double discriminant = 23 * 23 - 4 * 40 * (42 - xp);
        if (discriminant < 0) {
            throw new CalculationException("No positive solution for level");
        }
        double sqrtDiscriminant = Math.sqrt(discriminant);
        double x1 = (-23 + sqrtDiscriminant) / (2 * 40);
        double x2 = (-23 - sqrtDiscriminant) / (2 * 40);
        if (x1 >= 0) {
            return Math.round(x1);
        } else if (x2 >= 0) {
            return Math.round(x2);
        } else {
            throw new CalculationException("No positive solution for level");
        }
    }
}