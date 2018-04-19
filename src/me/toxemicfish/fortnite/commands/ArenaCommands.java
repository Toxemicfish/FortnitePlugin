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

            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage&7:"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena join"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena leave"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena create"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena remove"));
                return true;
            }

            if (args[0].equalsIgnoreCase("join")) {
                if (args.length != 1) {
                    p.sendMessage(ChatColor.RED + "Insuffcient arguments!");
                    return true;
                }

                int num = 0;
                try {
                    num = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + "Invalid arena ID");
                    return true;
                }

                ArenaManager.getManger().addPlayer(p, num);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have joined the arena!"));
                return true;
            } else if (args[0].equalsIgnoreCase("leave")) {
                ArenaManager.getManger().removePlayer(p);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have left the arena!"));
                return true;
            } else if (args[0].equalsIgnoreCase("create")) {

                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();

                ArenaManager.getManger().createArena(p.getLocation());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCreated arena at &e" + x + "&7,&e " + y + "&7,&e " + z));
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length != 1) {
                    p.sendMessage(ChatColor.RED + "Insuffcient arguments!");
                    return true;
                }

                int num = 0;
                try {
                    num = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + "Invalid arena ID");
                    return true;
                }
                ArenaManager.getManger().removeArena(num);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aArena &e" + num + " &a was removed"));
                return true;
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage&7:"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena join"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena leave"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena create"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &6/arena remove"));
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Player only command!");
        }
        return true;
    }

}
