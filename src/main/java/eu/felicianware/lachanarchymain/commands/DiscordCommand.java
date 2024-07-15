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
 * DiscordCommand is a command executor that provides the Discord server link to players.
 */
public class DiscordCommand implements CommandExecutor {

    private static final TextColor GOLD = NamedTextColor.GOLD;
    private static final String DISCORD_LINK = "https://discord.gg/WrkrRYb3mj";

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
        player.sendMessage(buildDiscordMessage());

        return true;
    }

    /**
     * Builds the Discord link message component.
     *
     * @return The Discord message as a Component
     */
    private Component buildDiscordMessage() {
        return Component.text()
                .append(Component.text("You can join the discord server here: ", GOLD))
                .append(Component.newline())
                .append(Component.text(DISCORD_LINK, GOLD))
                .build();
    }
}
