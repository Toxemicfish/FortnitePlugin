package me.toxemicfish.fortnite.commands;

import me.toxemicfish.fortnite.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(permission.getPermission(permission.RELOAD_CONFIG))) {

                if (args.length == 0) {
                    usage(p);
                    return true;
                }

                if (args[0].equalsIgnoreCase("lang")) {
                    langYML.reloadlangFile();
                    p.sendMessage(chatUtils.color(chatUtils.messageFromlangYML("reloadConfig")));
                    return true;
                }
                if (args[0].equalsIgnoreCase("game")) {
                    gameYML.reloadgameFile();
                    p.sendMessage(chatUtils.color(chatUtils.messageFromlangYML("reloadConfig")));
                    return true;
                } else {

                }

            } else {
                p.sendMessage(errors.getErrors(errors.NO_PERMISSION));
            }
        } else {
            sender.sendMessage(errors.getErrors(errors.PLAYER_ONLY));
        }
        return true;
    }

    public void usage(Player p) {
        p.sendMessage(chatUtils.color("&aUsage:"));
        p.sendMessage(chatUtils.color("&7- &e/lang &7Reloads lang file"));
        p.sendMessage(chatUtils.color("&7- &e/game &7Reloads game file"));
    }

}
