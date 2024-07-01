package eu.felicianware.lachanarchymain.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        Component discordMessage = Component.text()
                .append(Component.text("You can join the discord server here: ", NamedTextColor.GOLD))
                .append(Component.newline())
                .append(Component.text("https://discord.gg/WrkrRYb3mj", NamedTextColor.BLUE))
                .build();

        player.sendMessage(discordMessage);

        return true;
    }
}
