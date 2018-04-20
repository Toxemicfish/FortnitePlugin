package me.toxemicfish.fortnite;

import me.toxemicfish.fortnite.commands.joinCommand;
import me.toxemicfish.fortnite.commands.reloadConfig;
import me.toxemicfish.fortnite.core.Games.gameArena;
import me.toxemicfish.fortnite.utils.gameYML;
import me.toxemicfish.fortnite.utils.langYML;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private gameArena game;

    private static Main instance;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();


        if (langYML.langFile.exists())
            setupLangFile();

        if (gameYML.gameFile.exists())
            setupGameFile();

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
    }

    private void registerEvents() {
    }

    private void setupLangFile() {
        if (!langYML.getlang().contains("reloadConfig"))
            gameYML.getgame().set("reloadConfig", "&aThe file was reloaded!");
        if (!langYML.getlang().contains("gameStarted"))
            gameYML.getgame().set("gameStarted", "&aThe game is already started");
        if (!langYML.getlang().contains("lobbyAlready"))
            gameYML.getgame().set("lobbyAlready", "&aYou are already in a lobby");
    }

    private void setupGameFile() {
        if (!gameYML.getgame().contains("Settings.minPlayers"))
            gameYML.getgame().set("Settings.minPlayers", 3);

        if (!gameYML.getgame().contains("Settings.countdown"))
            gameYML.getgame().set("Settings.countdown", 10);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.world"))
            gameYML.getgame().set("Settings.humanSpawn.world", "world");

        if (!gameYML.getgame().contains("Settings.aliveSpawn.x"))
            gameYML.getgame().set("Settings.humanSpawn.x", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.y"))
            gameYML.getgame().set("Settings.humanSpawn.y", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.z"))
            gameYML.getgame().set("Settings.humanSpawn.z", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.yaw"))
            gameYML.getgame().set("Settings.humanSpawn.yaw", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.pitch"))
            gameYML.getgame().set("Settings.humanSpawn.pitch", 100);
        
        // Lobby
        if (!gameYML.getgame().contains("Settings.lobby.world"))
            gameYML.getgame().set("Settings.humanSpawn.world", "world");

        if (!gameYML.getgame().contains("Settings.lobby.x"))
            gameYML.getgame().set("Settings.humanSpawn.x", 100);

        if (!gameYML.getgame().contains("Settings.lobby.y"))
            gameYML.getgame().set("Settings.humanSpawn.y", 100);

        if (!gameYML.getgame().contains("Settings.lobby.z"))
            gameYML.getgame().set("Settings.humanSpawn.z", 100);

        if (!gameYML.getgame().contains("Settings.lobby.yaw"))
            gameYML.getgame().set("Settings.humanSpawn.yaw", 100);

        if (!gameYML.getgame().contains("Settings.lobby.pitch"))
            gameYML.getgame().set("Settings.humanSpawn.pitch", 100);
    }

    public static Main getInstance() {
        return instance;
    }

    public gameArena getGame() {
        return game;
    }
}
