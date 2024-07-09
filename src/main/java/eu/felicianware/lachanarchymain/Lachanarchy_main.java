package eu.felicianware.lachanarchymain;

import eu.felicianware.lachanarchymain.commands.DiscordCommand;
import eu.felicianware.lachanarchymain.commands.HelpCommand;
import eu.felicianware.lachanarchymain.commands.KillCommand;
import eu.felicianware.lachanarchymain.listeners.Antispam;
import eu.felicianware.lachanarchymain.listeners.CrystalDelay;
import eu.felicianware.lachanarchymain.listeners.JoinLeaveMessages;
import eu.felicianware.lachanarchymain.listeners.RandomSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lachanarchy_main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands.
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("kill").setExecutor(new KillCommand());

        // Register listeners/events.
        getServer().getPluginManager().registerEvents(new CrystalDelay(this), this);
        getServer().getPluginManager().registerEvents(new Antispam(this), this);
        getServer().getPluginManager().registerEvents(new RandomSpawn(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessages(this), this);

        getLogger().info("Lachanarchy Main enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Lachanarchy Main disabled");
    }
}
