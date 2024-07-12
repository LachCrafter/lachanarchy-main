package eu.felicianware.lachanarchymain.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeaveMessages implements Listener {

    private final Plugin plugin;

    public JoinLeaveMessages(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Component joinMessage = Component.text()
                .append(Component.text(player.getName(), NamedTextColor.DARK_GRAY))
                .append(Component.text(" joined.", NamedTextColor.DARK_GRAY))
                .build();

        event.joinMessage(null);
        Bukkit.getServer().sendMessage(joinMessage);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Component leaveMessage = Component.text()
                .append(Component.text(player.getName(), NamedTextColor.DARK_GRAY))
                .append(Component.text(" left.", NamedTextColor.DARK_GRAY))
                .build();

        event.quitMessage(null);
        Bukkit.getServer().sendMessage(leaveMessage);
    }
}
