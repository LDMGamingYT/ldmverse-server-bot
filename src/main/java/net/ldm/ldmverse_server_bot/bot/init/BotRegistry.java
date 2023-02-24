package net.ldm.ldmverse_server_bot.bot.init;

import net.ldm.ldmverse_server_bot.bot.command.PingCommand;
import net.ldm.ldmverse_server_bot.bot.command.SetLevelCommand;
import net.ldm.ldmverse_server_bot.bot.command.SuggestionCommand;
import net.ldm.ldmverse_server_bot.registry.Registries;
import net.ldm.ldmverse_server_bot.registry.Registry;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class BotRegistry {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotRegistry.class);
    public void initialize() {
        LOG.info("Registering items");
        Registry.register(Registries.COMMAND, new PingCommand());
        Registry.register(Registries.COMMAND, new SuggestionCommand());
        Registry.register(Registries.COMMAND, new SetLevelCommand());
    }
}