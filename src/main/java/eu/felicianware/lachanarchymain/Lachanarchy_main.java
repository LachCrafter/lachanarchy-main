package eu.felicianware.lachanarchymain;

import eu.felicianware.lachanarchymain.listeners.CrystalDelay;
import eu.felicianware.lachanarchymain.commands.DiscordCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lachanarchy_main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands.
        this.getCommand("discord").setExecutor(new DiscordCommand());

        // Register listeners/events.
        getServer().getPluginManager().registerEvents(new CrystalDelay(this), this);

        System.out.println("Lachanarchy_main loaded");
    }

    @Override
    public void onDisable() {
        System.out.println("Lachanarchy_main unloaded");
    }
}
