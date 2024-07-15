package eu.felicianware.lachanarchymain.listeners;

import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
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
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * CrystalDelay is a listener class that manages the delay for placing End Crystals
 * and handles their explosion events in the game.
 */

public class CrystalDelay implements Listener {

    private static final long PLACE_DELAY_MILLIS = 100;
    private static final long PLACE_DELAY_TICKS = PLACE_DELAY_MILLIS / 50;
    private static final long EXPLOSION_DELAY_TICKS = 4L;
    private static final float EXPLOSION_POWER = 6.0F;

    private final Plugin plugin;
    private final Set<UUID> placeCooldowns = new HashSet<>();

    /**
     * Constructs a new CrystalDelay listener.
     *
     * @param plugin The plugin instance
     */
    public CrystalDelay(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Handles the event when a player attempts to place an End Crystal.
     * Adds a cooldown to prevent rapid placement.
     *
     * @param event The PlayerInteractEvent
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCrystalPlace(@NotNull PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        final ItemStack interactItem = event.getItem();
        if (interactItem == null || interactItem.getType() != Material.END_CRYSTAL) return;

        final Player player = event.getPlayer();
        final UUID playerUUID = player.getUniqueId();

        if (placeCooldowns.contains(playerUUID)) {
            event.setCancelled(true);
            player.updateInventory();
        } else {
            placeCooldowns.add(playerUUID);
            RegionScheduler regionScheduler = plugin.getServer().getRegionScheduler();
            regionScheduler.runDelayed(plugin, player.getLocation(), handle -> placeCooldowns.remove(playerUUID), PLACE_DELAY_TICKS);
        }
    }

    /**
     * Handles the event when an End Crystal is damaged by a player.
     * Cancels the damage and creates a delayed explosion.
     *
     * @param event The EntityDamageByEntityEvent
     */
    @EventHandler
    public void onEntityDamageByEntity(@NotNull EntityDamageByEntityEvent event) {
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
                world.createExplosion(loc, EXPLOSION_POWER, false, true);
            }
        }, EXPLOSION_DELAY_TICKS);
    }
}
