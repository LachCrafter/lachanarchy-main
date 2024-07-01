package eu.felicianware.lachanarchymain;

import eu.felicianware.lachanarchymain.listeners.CrystalDelay;
import eu.felicianware.lachanarchymain.commands.DiscordCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class Lachanarchy_main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands.
        this.getCommand("discord").setExecutor(new DiscordCommand());

        // Register listeners/events.
        getServer().getPluginManager().registerEvents(new CrystalDelay(this), this);

        getLogger().info("Lachanarchy Main enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Lachanarchy Main disabled");
    }
}
