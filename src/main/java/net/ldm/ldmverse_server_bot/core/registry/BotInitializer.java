package net.ldm.ldmverse_server_bot.core.registry;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.ldm.ldmverse_server_bot.bot.command.Command;
import net.ldm.ldmverse_server_bot.bot.init.BotHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.ArrayList;

/**
 * This should <strong>ONLY</strong> be called by a derived class, <br>
 * typically by extending this class.
 */
public class BotInitializer {
    private static final Logger LOG = LoggerContext.getContext().getLogger(BotInitializer.class);

    /**
     * When overriding this method, call to super must be <strong>last</strong>.
     */
    public void initialize() {
        // Initialize all commands to JDA
        ArrayList<CommandData> commands = new ArrayList<>();

        for (RegistryObject object: Registry.getRegisteredFrom(Registries.COMMAND)) {
            if (!(object instanceof Command registerCommand)) {
                LOG.warn("Non-command registry object found in command registry, skipping");
                return;
            }

            commands.add(Commands.slash(registerCommand.getCommand(), registerCommand.getDescription())
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(registerCommand.getPermissions()))
                    .setGuildOnly(registerCommand.isGuildOnly()).addOptions(registerCommand.getOptions()));
        }

        BotHandler.bot.updateCommands().addCommands(commands).queue();
        LOG.info("Registered {} commands", commands.size());
    }
}
