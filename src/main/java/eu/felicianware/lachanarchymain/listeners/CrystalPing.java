package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class CrystalPing implements Listener {

    private final Plugin plugin;
    private final Map<Entity, Long> crystalSpawnTicks = new HashMap<>();

    public CrystalPing(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCrystalSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.END_CRYSTAL) {
            crystalSpawnTicks.put(event.getEntity(), getCurrentTick());
        }
    }

    @EventHandler
    public void onCrystalDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (event.getEntityType() != EntityType.END_CRYSTAL) {
            return;
        }

        Entity crystal = event.getEntity();
        Long spawnTick = crystalSpawnTicks.get(crystal);
        if (spawnTick == null) {
            return;
        }

        long ticksExisted = getCurrentTick() - spawnTick;
        if (ticksExisted < 4) {
            long delayTicks = 4 - ticksExisted;
            new BukkitRunnable() {
                @Override
                public void run() {
                    handleCrystalBreak(event);
                }
            }.runTaskLater(plugin, delayTicks);
            event.setCancelled(true);
        } else {
            handleCrystalBreak(event);
        }
    }

    private void handleCrystalBreak(EntityDamageByEntityEvent event) {
        Entity crystal = event.getEntity();
        if (crystal.isDead()) {
            return;
        }

        crystal.remove();
        event.setCancelled(true);
        broadcastCrystalBreak(event);
    }

    private void broadcastCrystalBreak(EntityDamageByEntityEvent event) {
        Bukkit.getScheduler().runTask(plugin, () -> event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 6F, false, true));
    }

    private long getCurrentTick() {
        return Bukkit.getServer().getCurrentTick();
    }
}
