package me.toxemicfish.fortnite.utils;

import me.toxemicfish.fortnite.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class langYML {

    static Main plugin;

    public langYML(Main main) {
        main = main;
    }

    public static YamlConfiguration lang = null;
    public static File langFile = null;

    public static void reloadlangFile() {
        if (langFile == null) {
            langFile = new File(Bukkit.getPluginManager().getPlugin("Fortnite").getDataFolder(), "lang.yml");
        }
        lang = YamlConfiguration.loadConfiguration(langFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Fortnite").getResource("lang.yml");
        if (defConfigStream != null) {
            YamlConfiguration defconfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!langFile.exists() || langFile.length() == 0L) {
                lang.setDefaults(defconfig);
            }
        }
    }

    public static FileConfiguration getlang() {
        if (lang == null) {
            reloadlangFile();
        }
        return lang;
    }

    public static void savelang() {
        if (lang == null || langFile == null) {
            return;
        }
        try {
            getlang().save(langFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + langFile, ex);
        }
    }
}
