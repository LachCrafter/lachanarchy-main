package eu.felicianware.lachanarchymain.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.naming.Name;

public class HelpCommand implements CommandExecutor {


    private static final TextColor WHITE = NamedTextColor.WHITE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        Component helpMessage = Component.text()
                .append(Component.text("------- ", NamedTextColor.GOLD)
                        .append(Component.text("Commands", NamedTextColor.DARK_AQUA))
                        .append(Component.text(" -------", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("/help", NamedTextColor.GOLD)
                        .append(Component.text(" - Shows this help menu.", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("/kill", NamedTextColor.GOLD)
                        .append(Component.text(" - Kill yourself.", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("/msg", NamedTextColor.GOLD)
                        .append(Component.text(" - Message a player.", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("/ignore", NamedTextColor.GOLD)
                        .append(Component.text(" - Ignore a player in chat.", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("/stats", NamedTextColor.GOLD)
                        .append(Component.text(" - Show the server stats.", NamedTextColor.GOLD)))
                .append(Component.newline())
                .append(Component.text("-------", NamedTextColor.GOLD)
                        .append(Component.text("----------", NamedTextColor.GOLD))
                        .append(Component.text("------", NamedTextColor.GOLD)))
                .build();

        player.sendMessage(helpMessage);

        return true;
    }

}
