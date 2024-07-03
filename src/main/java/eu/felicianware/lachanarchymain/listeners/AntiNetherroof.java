package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiNetherroof implements Listener {
    private final JavaPlugin plugin;

    // Constructor to pass the plugin instance
    public AntiNetherroof(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Detect when a player moves
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (isOnNetherRoof(location)) {
            teleportBelowRoof(player);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location location = event.getTo();

        if (isOnNetherRoof(location)) {
            teleportBelowRoof(player);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (isOnNetherRoof(location)) {
            event.setCancelled(true);
            player.sendMessage("You cannot build on the Nether roof!");
        }
    }

    private boolean isOnNetherRoof(Location location) {
        World world = location.getWorld();
        if (world == null || !world.getEnvironment().equals(World.Environment.NETHER)) {
            return false;
        }
        return location.getY() >= world.getMaxHeight() - 2;
    }

    // Teleport the player below the Nether roof
    private void teleportBelowRoof(Player player) {
        World world = player.getWorld();
        Location safeLocation = new Location(world, player.getLocation().getX(), 120, player.getLocation().getZ());

        // Ensure the location is safe (not inside a block)
        while (safeLocation.getBlock().getType() != Material.AIR) {
            safeLocation.setY(safeLocation.getY() - 1);
        }

        player.teleport(safeLocation);
    }
}
