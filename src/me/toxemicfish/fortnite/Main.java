package me.toxemicfish.fortnite;

import me.toxemicfish.fortnite.commands.joinCommand;
import me.toxemicfish.fortnite.commands.reloadConfig;
import me.toxemicfish.fortnite.commands.setConfigs;
import me.toxemicfish.fortnite.core.Games.gameArena;
import me.toxemicfish.fortnite.events.deathEvent;
import me.toxemicfish.fortnite.utils.gameYML;
import me.toxemicfish.fortnite.utils.langYML;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private gameArena game;

    private static Main instance;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

/*
        if (!langYML.langFile.exists())
            setupLangFile();

        if (!gameYML.gameFile.exists())
            setupGameFile();
*/
        instance = this;

        Bukkit.setSpawnRadius(0);
        World world = Bukkit.getWorlds().get(0);

        world.setSpawnFlags(false, false);
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("doDaylightCycle", "false");

        registerCommands();
        registerEvents();

        game = new gameArena(this);

    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void registerCommands() {
        getCommand("reloadconfig").setExecutor(new reloadConfig());
        getCommand("join").setExecutor(new joinCommand());
        getCommand("setup").setExecutor(new setConfigs());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new deathEvent(this, "deathEvent registered"), this);
    }

    public static Main getInstance() {
        return instance;
    }

    public gameArena getGame() {
        return game;
    }
}
