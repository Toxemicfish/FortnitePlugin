package me.toxemicfish.fortnite.commands;

import me.toxemicfish.fortnite.Main;
import me.toxemicfish.fortnite.core.Games.gameArena;
import me.toxemicfish.fortnite.utils.chatUtils;
import me.toxemicfish.fortnite.utils.errors;
import me.toxemicfish.fortnite.utils.gameYML;
import me.toxemicfish.fortnite.utils.permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class joinCommand implements CommandExecutor {

    private Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            gameArena gameArena = plugin.getGame();
            if (p.hasPermission(permission.getPermission(permission.GAME))) {
                if (gameArena.isGameRunning()) {
                    p.sendMessage(chatUtils.color(chatUtils.messageFromlangYML("gameStarted")));
                    return true;
                }

                if (!gameArena.getLobby().contains(p)) {
                    gameArena.getLobby().add(p);
                    gameArena.sendMessageLobby("&e" + p.getName() + " &3has joined the game. SLOT &7(&e" + gameArena.getLobby().size() + "&7/&e" + gameArena.getMINIMUM_PLAYERS() + "&7)");

                    World world = Bukkit.getServer().getWorld(gameYML.getgame().getString("Settings.lobby.world"));
                    double x = gameYML.getgame().getDouble("Settings.lobby.x");
                    double y = gameYML.getgame().getDouble("Settings.lobby.y");
                    double z = gameYML.getgame().getDouble("Settings.lobby.z");
                    float yaw = gameYML.getgame().getInt("Settings.lobby.yaw");
                    float pitch = gameYML.getgame().getInt("Settings.lobby.pitch");

                    Location loc = new Location(world, x, y, z, yaw, pitch);

                    p.teleport(loc);

                    if (gameArena.shouldStart()) {
                        gameArena.startCountdown();
                    }

                    return true;
                } else {
                    p.sendMessage(chatUtils.color(chatUtils.messageFromlangYML("lobbyAlready")));
                }
                return true;
            } else {
                sender.sendMessage(errors.getErrors(errors.NO_PERMISSION));
            }

        } else {
            sender.sendMessage(errors.getErrors(errors.PLAYER_ONLY));
        }
        return true;
    }

}
