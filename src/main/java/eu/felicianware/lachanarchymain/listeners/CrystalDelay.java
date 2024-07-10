package eu.felicianware.lachanarchymain.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CrystalDelay implements Listener {
    private final Plugin plugin;
    private final Set<UUID> placeCooldowns = new HashSet<>();

    public CrystalDelay(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCrystalPlace(PlayerInteractEvent event) {      
        long placeDelayMillis = 100;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        final ItemStack interactItem = event.getItem();
        if (interactItem == null || interactItem.getType() != Material.END_CRYSTAL) return;

        final Player player = event.getPlayer();
        final UUID playerUUID = player.getUniqueId();

        if (placeCooldowns.contains(playerUUID)) {
            event.setCancelled(true);
            boolean updateInv = true;
            if (updateInv) player.updateInventory();
        } else {
            placeCooldowns.add(playerUUID);
            RegionScheduler regionScheduler = plugin.getServer().getRegionScheduler();

            regionScheduler.runDelayed(plugin, player.getLocation(), handle -> placeCooldowns.remove(playerUUID), placeDelayMillis / 50);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal)) return;
        if (!(event.getDamager() instanceof Player)) return;

        event.setCancelled(true);

        final EnderCrystal crystal = (EnderCrystal) event.getEntity();
        final Location loc = crystal.getLocation();
        final World world = loc.getWorld();
        if (world == null) return;

        RegionScheduler regionScheduler = plugin.getServer().getRegionScheduler();

        regionScheduler.runDelayed(plugin, loc, handle -> {
            if (!crystal.isDead()) {
                crystal.remove();
                world.createExplosion(loc, 6.0F, false, true);
            }
        }, 4L);
    }
}
