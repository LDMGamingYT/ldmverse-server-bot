package net.ldm.ldmverse_server_bot.core.registry;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.ldm.ldmverse_server_bot.bot.command.Command;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * This should <strong>ONLY</strong> be called by a derived class, <br>
 * typically by extending this class.
 */
public class BotInitializer {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotInitializer.class);

    /**
     * When overriding this method, call to super <strong>must</strong> be first.
     */
    public void initialize() {
        // Initialize all commands to JDA
        CommandListUpdateAction action = BotHandler.bot.updateCommands();
        int commandsRegistered = 0;
        for (RegistryObject object: Registry.getRegisteredFrom(Registries.COMMAND)) {
            if (!(object instanceof Command registerCommand)) {
                LOG.warn("Non-command registry object found in command registry, skipping");
                return;
            }

            Commands.slash(registerCommand.getCommand(), registerCommand.getDescription())
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(registerCommand.getPermissions()))
                    .setGuildOnly(registerCommand.isGuildOnly()).addOptions(registerCommand.getOptions());

            commandsRegistered++;
        }
        action.queue();
        LOG.info("Registered {} commands", commandsRegistered);
    }
}
