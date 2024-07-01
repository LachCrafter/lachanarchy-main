package eu.felicianware.lachanarchymain.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Formatter;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GOLD + "You can join the Discord Server here:https://discord.gg/WrkrRYb3mj");
            }
            return true;
        }
        return false;
    }
}
