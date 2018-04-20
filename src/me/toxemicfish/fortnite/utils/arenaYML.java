package me.toxemicfish.fortnite.utils;

import me.toxemicfish.fortnite.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class arenaYML {

    static Main plugin;

    public arenaYML(Main main) {
        main = main;
    }

    public static YamlConfiguration arena = null;
    public static File arenaFile = null;

    public static void reloadArenasFile() {
        if (arenaFile == null) {
            arenaFile = new File(Bukkit.getPluginManager().getPlugin("Fortnite").getDataFolder(), "arenas.yml");
        }
        arena = YamlConfiguration.loadConfiguration(arenaFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Fortnite").getResource("arenas.yml");
        if (defConfigStream != null) {
            YamlConfiguration defconfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!arenaFile.exists() || arenaFile.length() == 0L) {
                arena.setDefaults(defconfig);
            }
        }
    }

    public static FileConfiguration getArenas() {
        if (arena == null) {
            reloadArenasFile();
        }
        return arena;
    }

    public static void saveArenas() {
        if (arena == null || arenaFile == null) {
            return;
        }
        try {
            getArenas().save(arenaFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + arenaFile, ex);
        }
    }
}
