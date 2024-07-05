package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class CrystalDelay implements Listener {
    private final Plugin plugin;

    public CrystalDelay(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal))
            return;
        if (!(event.getDamager() instanceof Player))
            return;

        event.setCancelled(true);

        final EnderCrystal crystal = (EnderCrystal) event.getEntity();
        final Location loc = crystal.getLocation();
        final World world = loc.getWorld();
        if (world == null)
            return;

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (!crystal.isDead()) {
                            crystal.remove();
                            world.createExplosion(loc, 4.0F, false, true);
                        }
                    }
                });
            }
        }, 4L);
    }
}
