package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CrystalDelay implements Listener {
    private final Plugin plugin;

    public CrystalDelay(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal))
            return;
        if (!(event.getDamager() instanceof org.bukkit.entity.Player))
            return;
        event.setCancelled(true);
        final EnderCrystal crystal = (EnderCrystal) event.getEntity();
        final Location loc = crystal.getLocation();
        final World world = loc.getWorld();
        if (world == null)
            return;
        new BukkitRunnable() {
            public void run() {
                if (crystal.isDead())
                    return;
                crystal.remove();
                world.createExplosion(loc, 4.0F, false, true);
            }
        }.runTaskLater(plugin, 4L);
    }
}
