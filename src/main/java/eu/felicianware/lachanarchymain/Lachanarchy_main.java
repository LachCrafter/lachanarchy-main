package eu.felicianware.lachanarchymain;

import eu.felicianware.lachanarchymain.commands.DiscordCommand;
import eu.felicianware.lachanarchymain.commands.HelpCommand;
import eu.felicianware.lachanarchymain.commands.KillCommand;
import eu.felicianware.lachanarchymain.listeners.AntiNetherroof;
import eu.felicianware.lachanarchymain.listeners.CrystalDelay;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class Lachanarchy_main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands.
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("kill").setExecutor(new KillCommand());

        // Register listeners/events.
        getServer().getPluginManager().registerEvents(new CrystalDelay(this), this);
        getServer().getPluginManager().registerEvents(new AntiNetherroof(this), this);

        getLogger().info("Lachanarchy Main enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Lachanarchy Main disabled");
    }
}
