package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class RandomSpawn  implements Listener {

    private final Random random = new Random();
    private final Plugin plugin;

    public RandomSpawn(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        Location randomLocation = getRandomSafeLocation(world, 200, 200);
        if (randomLocation != null) {
            event.setRespawnLocation(randomLocation);
        }
    }

    private Location getRandomSafeLocation(World world, int maxX, int maxZ) {
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(maxX * 2) - maxX;
            int z = random.nextInt(maxZ * 2) - maxZ;
            int y = world.getHighestBlockYAt(x, z) + 1;

            Location location = new Location(world, x, y, z);

            if (isLocationSafe(location)) {
                return location;
            }
        }
        return null;
    }

    private boolean isLocationSafe(Location location) {
        Material blockAtLocation = location.getBlock().getType();
        Material blockBelow = location.clone().add(0, -1, 0).getBlock().getType();

        return blockAtLocation == Material.AIR && blockBelow != Material.AIR;
    }
}
