package me.toxemicfish.fortnite.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class consoleUtils {

    static String prefix = chatUtils.getPrefix();

    public static void consoleGood(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + ChatColor.GREEN + ChatColor.BOLD + msg);
    }

    public static void consoleWarn(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + ChatColor.BOLD + msg);
    }

    public static void consoleBad(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + msg);
    }

    public static void consoleGInfo(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + ChatColor.AQUA + ChatColor.BOLD + msg);
    }

}
