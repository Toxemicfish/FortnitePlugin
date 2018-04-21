package me.toxemicfish.fortnite.commands;

import me.toxemicfish.fortnite.Main;
import me.toxemicfish.fortnite.utils.errors;
import me.toxemicfish.fortnite.utils.gameYML;
import me.toxemicfish.fortnite.utils.langYML;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setConfigs implements CommandExecutor {

    Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            setupGameFile();
            setupLangFile();
        } else {
            sender.sendMessage(errors.getErrors(errors.PLAYER_ONLY));
        }
        return true;
    }

    public static void setupLangFile() {
        langYML.getlang().set("reloadConfig", "&aThe file was reloaded!");
        langYML.getlang().set("gameStarted", "&aThe game is already started");
        langYML.getlang().set("lobbyAlready", "&aYou are already in a lobby");

        langYML.savelang();
        langYML.reloadlangFile();
    }


    public static void setupGameFile() {
        if (!gameYML.getgame().contains("Settings.minPlayers"))
            gameYML.getgame().set("Settings.minPlayers", 2);

        if (!gameYML.getgame().contains("Settings.countdown"))
            gameYML.getgame().set("Settings.countdown", 10);

        if (!gameYML.getgame().contains("Settings.world"))
            gameYML.getgame().set("Settings.world", "world");

        if (!gameYML.getgame().contains("Settings.aliveSpawn.world"))
            gameYML.getgame().set("Settings.aliveSpawn.world", "world");

        if (!gameYML.getgame().contains("Settings.aliveSpawn.x"))
            gameYML.getgame().set("Settings.aliveSpawn.x", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.y"))
            gameYML.getgame().set("Settings.aliveSpawn.y", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.z"))
            gameYML.getgame().set("Settings.aliveSpawn.z", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.yaw"))
            gameYML.getgame().set("Settings.aliveSpawn.yaw", 100);

        if (!gameYML.getgame().contains("Settings.aliveSpawn.pitch"))
            gameYML.getgame().set("Settings.aliveSpawn.pitch", 100);

        // Lobby
        if (!gameYML.getgame().contains("Settings.lobby.world"))
            gameYML.getgame().set("Settings.lobby.world", "world");

        if (!gameYML.getgame().contains("Settings.lobby.x"))
            gameYML.getgame().set("Settings.lobby.x", 100);

        if (!gameYML.getgame().contains("Settings.lobby.y"))
            gameYML.getgame().set("Settings.lobby.y", 100);

        if (!gameYML.getgame().contains("Settings.lobby.z"))
            gameYML.getgame().set("Settings.lobby.z", 100);

        if (!gameYML.getgame().contains("Settings.lobby.yaw"))
            gameYML.getgame().set("Settings.lobby.yaw", 100);

        if (!gameYML.getgame().contains("Settings.lobby.pitch"))
            gameYML.getgame().set("Settings.lobby.pitch", 100);

        gameYML.savegame();
        gameYML.reloadgameFile();
    }

}
