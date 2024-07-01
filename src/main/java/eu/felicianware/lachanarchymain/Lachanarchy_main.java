package eu.felicianware.lachanarchymain;

import org.bukkit.plugin.java.JavaPlugin;

public final class Lachanarchy_main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Lachanarchy_main is enabled");

        // Register commands.
        this.getCommand("discord").setExecutor(new eu.felicianware.lachanarchymain.commands.DiscordCommand());

        // Register listeners.


    }

    @Override
    public void onDisable() {
        System.out.println("Lachanarchy_main is disabled");
    }
}
