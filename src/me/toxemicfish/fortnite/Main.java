package me.toxemicfish.fortnite;

import me.toxemicfish.fortnite.Managers.ArenaManager;
import me.toxemicfish.fortnite.commands.ArenaCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {

        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(getDataFolder() == null)
            saveDefaultConfig();

        new ArenaManager(this);
        //ArenaManager.getManger().loadGames();

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new ArenaCommands());
    }

    private void registerEvents() {
    }
}
