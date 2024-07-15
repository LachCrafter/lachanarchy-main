package eu.felicianware.lachanarchymain.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * HelpCommand is a command executor that provides a help menu for players.
 */
public class HelpCommand implements CommandExecutor {

    private static final TextColor GOLD = NamedTextColor.GOLD;
    private static final TextColor DARK_AQUA = NamedTextColor.DARK_AQUA;

    /**
     * Executes the given command, returning its success.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(buildHelpMessage());

        return true;
    }

    /**
     * Builds the help message component.
     *
     * @return The help message as a Component
     */
    private Component buildHelpMessage() {
        return Component.text()
                .append(Component.text("------- ", GOLD))
                .append(Component.text("Commands", DARK_AQUA))
                .append(Component.text(" -------", GOLD))
                .append(Component.newline())
                .append(createCommandDescription("/help", "Shows this help menu."))
                .append(Component.newline())
                .append(createCommandDescription("/kill", "Kill yourself."))
                .append(Component.newline())
                .append(createCommandDescription("/msg", "Message a player."))
                .append(Component.newline())
                .append(createCommandDescription("/ignore", "Ignore a player in chat."))
                .append(Component.newline())
                .append(createCommandDescription("/stats", "Show the server stats."))
                .append(Component.newline())
                .append(Component.text("-------", GOLD))
                .append(Component.text("----------", GOLD))
                .append(Component.text("------", GOLD))
                .build();
    }

    /**
     * Creates a formatted command description component.
     *
     * @param command The command text
     * @param description The description of the command
     * @return A Component containing the formatted command and description
     */
    private Component createCommandDescription(String command, String description) {
        return Component.text()
                .append(Component.text(command, DARK_AQUA))
                .append(Component.text(" - " + description, DARK_AQUA))
                .build();
    }
}
