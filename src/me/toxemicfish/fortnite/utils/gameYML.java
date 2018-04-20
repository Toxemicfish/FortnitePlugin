package me.toxemicfish.fortnite.utils;

import me.toxemicfish.fortnite.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class gameYML {

    static Main plugin;

    public gameYML(Main main) {
        main = main;
    }

    public static YamlConfiguration game = null;
    public static File gameFile = null;

    public static void reloadgameFile() {
        if (gameFile == null) {
            gameFile = new File(Bukkit.getPluginManager().getPlugin("Fortnite").getDataFolder(), "game.yml");
        }
        game = YamlConfiguration.loadConfiguration(gameFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Fortnite").getResource("game.yml");
        if (defConfigStream != null) {
            YamlConfiguration defconfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!gameFile.exists() || gameFile.length() == 0L) {
                game.setDefaults(defconfig);
            }
        }
    }

    public static FileConfiguration getgame() {
        if (game == null) {
            reloadgameFile();
        }
        return game;
    }

    public static void savegame() {
        if (game == null || gameFile == null) {
            return;
        }
        try {
            getgame().save(gameFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + gameFile, ex);
        }
    }
}
