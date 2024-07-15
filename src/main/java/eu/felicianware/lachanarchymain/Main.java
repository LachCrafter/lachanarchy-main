package eu.felicianware.lachanarchymain;

import eu.felicianware.lachanarchymain.commands.DiscordCommand;
import eu.felicianware.lachanarchymain.commands.HelpCommand;
import eu.felicianware.lachanarchymain.commands.KillCommand;
import eu.felicianware.lachanarchymain.listeners.CrystalDelay;
import eu.felicianware.lachanarchymain.listeners.JoinLeaveMessages;
import eu.felicianware.lachanarchymain.listeners.RandomSpawn;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("kill").setExecutor(new KillCommand());

        // Register listeners
        getServer().getPluginManager().registerEvents(new CrystalDelay(this), this);
        getServer().getPluginManager().registerEvents(new RandomSpawn(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessages(this), this);

        // Call events

        getLogger().info("2b2t-core enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("2b2t-core disabled");
    }
}
