package net.ldm.ldmverse_server_bot.resource;

public class RemoteResourceGetter {
    private static final String GITHUB_RESOURCES_PATH = "https://github.com/LDMGamingYT/ldmverse-server-bot/blob/master/src/main/resources/%s?raw=true";

    public static String getPathToResource(String path) {
        return String.format(GITHUB_RESOURCES_PATH, path);
    }
}
