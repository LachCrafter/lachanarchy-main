package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class CrystalDelay implements Listener {
    private final Plugin plugin;

    public CrystalDelay(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && clickedBlock.getType() == Material.END_CRYSTAL) {
                long placeTime = System.currentTimeMillis();

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (clickedBlock.getType() == Material.END_CRYSTAL) {
                        long breakTime = System.currentTimeMillis();
                        long timeDifference = breakTime - placeTime;

                        if (timeDifference > 100) { // 250ms = 5 ticks
                            event.setCancelled(true);
                        }
                    }
                }, 3L);
            }
        }
    }
}
