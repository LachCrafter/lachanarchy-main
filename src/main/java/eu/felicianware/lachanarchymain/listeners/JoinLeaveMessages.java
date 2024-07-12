package eu.felicianware.lachanarchymain.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeaveMessages implements Listener {

    public JoinLeaveMessages(Plugin plugin) {
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Player joiningPlayer = event.getPlayer();
        Component joinMessage = Component.text(joiningPlayer.displayName() + " joined.", NamedTextColor.DARK_GRAY);

        Bukkit.getServer().sendMessage(joinMessage);

    }

    @EventHandler
    public void onLeave(final PlayerQuitEvent event) {
        Player leaveingPlayer = event.getPlayer();
        Component leaveMessage = Component.text(leaveingPlayer.displayName() + " left.", NamedTextColor.DARK_GRAY);

        Bukkit.getServer().sendMessage(leaveMessage);

    }


}
