package me.toxemicfish.fortnite;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void registerCommands() {
    }

    private void registerEvents() {
    }
}
