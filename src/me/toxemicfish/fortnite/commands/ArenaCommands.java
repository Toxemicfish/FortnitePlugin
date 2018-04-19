package me.toxemicfish.fortnite.commands;

import me.toxemicfish.fortnite.Managers.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage&7:"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena join"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena leave"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena create"));
                return true;
            }

            if (args[0].equalsIgnoreCase("join")) {
                int num = 0;
                try {
                    num = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + "Invalid arena ID");
                    return true;
                }

                ArenaManager.getManger().addPlayer(p, num);
            } else if(args[0].equalsIgnoreCase("leave")) {
                ArenaManager.getManger().removePlayer(p);
            } else if(args[0].equalsIgnoreCase("create")) {
                ArenaManager.getManger().createArena(p.getLocation());
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage&7:"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena join"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena leave"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena create"));
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Player only command!");
        }
        return true;
    }

}
