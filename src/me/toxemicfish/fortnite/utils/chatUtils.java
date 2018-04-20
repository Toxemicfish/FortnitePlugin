package me.toxemicfish.fortnite.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class chatUtils {

    private static String prefix = color("&7[&eFortnite&7]&f ");

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String formatWithPrefix(String msg) {
        return color(prefix + msg);
    }

    public static String messageFromlangYML(String string) {
        if (langYML.getlang().contains(string)) {
            return langYML.getlang().getString(color(string));
        }
        return String.valueOf(Bukkit.getServer().broadcastMessage(color(prefix + "&cPlease add the string: &a" + string + " &cto the lang file")));
    }

    public static String getPrefix() {
        return prefix;
    }
}
