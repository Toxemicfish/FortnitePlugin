package me.toxemicfish.fortnite.core.Games;

import me.toxemicfish.fortnite.Main;
import me.toxemicfish.fortnite.core.States.gameState;
import me.toxemicfish.fortnite.utils.chatUtils;
import me.toxemicfish.fortnite.utils.gameYML;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class gameArena {

    private Main plugin;

    public gameArena(Main plugin) {
        state = gameState.WAITING;
    }

    private gameState state;

    Configuration config = gameYML.getgame();

    World aliveSpawnWorld = Bukkit.getServer().getWorld(config.getString("Settings.aliveSpawn.world"));
    double aliveSpawnX = config.getDouble("Settings.aliveSpawn.x");
    double aliveSpawnY = config.getDouble("Settings.aliveSpawn.y");
    double aliveSpawnZ = config.getDouble("Settings.aliveSpawn.z");
    float aliveSpawnYAW = config.getInt("Settings.aliveSpawn.yaw");
    float aliveSpawnPITCH = config.getInt("Settings.aliveSpawn.pitch");

    Location aliveSpawn = new Location(aliveSpawnWorld, aliveSpawnX, aliveSpawnY, aliveSpawnZ, aliveSpawnYAW, aliveSpawnPITCH);


    private int MINIMUM_PLAYERS = gameYML.getgame().getInt("Settings.minPlayers");

    private HashSet<Player> lobby = new HashSet<>();

    private HashSet<Player> alive = new HashSet<>();

    private String prefix = chatUtils.getPrefix();

    public HashSet<Player> getLobby() {
        return lobby;
    }

    public boolean isAlive(Player player) {
        return alive.contains(player);
    }

    public int getAliveSize() {
        return alive.size();
    }

    public void addAlive(Player player) {
        alive.add(player);
    }

    public void removeAlive(Player player) {
        alive.remove(player);
    }

    public boolean isLobby(Player player) {
        return lobby.contains(player);
    }

    public int getLobbySize() {
        return lobby.size();
    }

    public void addLobby(Player player) {
        lobby.add(player);
    }

    public void removeLobby(Player player) {
        lobby.remove(player);
    }

    public void removeAllLobby() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            lobby.remove(all);
        }
    }

    public boolean isGameRunning() {
        return state == gameState.RUNNING;
    }

    public boolean isMinimumMet() {
        return lobby.size() >= MINIMUM_PLAYERS;
    }

    public boolean shouldStart() {
        return state == gameState.WAITING && isMinimumMet();
    }

    public boolean shouldEnd() {
        return (alive.size() == 1) && isGameRunning();
    }

    public int getMINIMUM_PLAYERS() {
        return MINIMUM_PLAYERS;
    }

    public void sendMessageAlive(String msg) {
        for (Player alive : Bukkit.getOnlinePlayers()) {
            if (isAlive(alive)) {
                alive.sendMessage(chatUtils.color(prefix + msg));
            }
        }
    }

    public void sendMessageLobby(String msg) {
        for (Player lobby : Bukkit.getOnlinePlayers()) {
            if (isLobby(lobby)) {
                lobby.sendMessage(chatUtils.color(prefix + msg));
            }
        }
    }

    public void startCountdown() {
        state = gameState.STARTING;

        new BukkitRunnable() {
            int countdown = gameYML.getgame().getInt("Settings.countdown");

            @Override
            public void run() {
                if (countdown != 0) {
                    if (countdown == 100 || countdown == 90 || countdown == 80 || countdown == 70 || countdown == 60 || countdown == 50 || countdown == 40 || countdown == 30 || countdown == 20 || countdown == 10 || countdown <= 5 && countdown > 0) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Game will start in " + ChatColor.RED + countdown + ChatColor.YELLOW + " seconds");
                    }

                    countdown--;
                } else {
                    cancel();

                    if (state == gameState.STARTING && isMinimumMet()) {
                        startGame();
                    } else {
                        state = gameState.WAITING;
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void startGame() {
        state = gameState.RUNNING;

        setupPlayers();
        removeAllLobby();
        sendPlayers();

        sendMessageAlive("&aThe game has started fight to end. See who is the last to survive!!");

        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (!(entity instanceof Player) && (entity instanceof Animals || entity instanceof Monster)) {
                entity.remove();
            }
        }

    }

    public void endGame() {
        state = gameState.WAITING;

        if (getAliveSize() == 1) {
            Bukkit.broadcastMessage(chatUtils.color("&e" + alive.toString() + "&a Has just won a game!"));
        }

        lobby.clear();
        alive.clear();
    }

    private void setupPlayers() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (isLobby(players)) {
                players.getInventory().clear();
                players.getInventory().setArmorContents(null);
                players.setGameMode(GameMode.SURVIVAL);
            }
        }
    }


    private void sendPlayers() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (isAlive(players)) {
                players.teleport(aliveSpawn);
                players.sendMessage(chatUtils.color("&aFight to the death!"));
            }
        }
    }
}
