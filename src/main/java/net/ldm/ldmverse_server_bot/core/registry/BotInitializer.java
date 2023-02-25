package net.ldm.ldmverse_server_bot.core.registry;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
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

            SlashCommandData commmand = Commands.slash(registerCommand.getCommand(), registerCommand.getDescription())
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(registerCommand.getPermissions()))
                    .setGuildOnly(registerCommand.isGuildOnly()).addOptions(registerCommand.getOptions());

            /*if (registerCommand.getOptions().length == 0) return;
            for (OptionData option: registerCommand.getOptions()) {
                commmand = commmand.addOptions(new OptionData(option.getType(), option.getName(), option.getDescription(),
                        option.isRequired()).addChoices());
            }*/

            commandsRegistered++;
        }
        action.queue();
        LOG.info("Registered {} commands", commandsRegistered);
    }

    private static void registerCommands(JDA bot) {
        bot.updateCommands().addCommands(
                Commands.slash("ping", "Ping? Pong!"),
                Commands.slash("suggestion", "Set the status of a suggestion.")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .setGuildOnly(true)
                        .addOptions(new OptionData(OptionType.STRING, "status", "The status of the suggestion", true)
                                .addChoice("approve", "approve")
                                .addChoice("deny", "deny")
                                .addChoice("consider", "consider")
                                .addChoice("implement", "implement")),
                Commands.slash("set-level", "Sets a user's level")
                        .addOption(OptionType.INTEGER, "level", "The new level", true)
                // TODO: 2023-02-23 Add commands from 'Sapphire' bot
        ).queue();
        LOG.info("Registered {} commands", bot.retrieveCommands().complete().size());
    }
}
