package me.toxemicfish.fortnite.Managers;

import me.toxemicfish.fortnite.Main;
import me.toxemicfish.fortnite.utils.arenaYML;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ArenaManager {

    // Singleton instance
    private static ArenaManager am = new ArenaManager();

    // Player data
    private final Map<String, Location> locs = new HashMap<String, Location>();
    private final Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
    private final Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();

    List<Arena> arenas = new ArrayList<Arena>();

    static Main plugin;

    public ArenaManager(Main main) {
        main = main;
    }

    // Keeps track of the current arena ID
    private int arenaSize = 0;

    public ArenaManager() {

    }

    public static ArenaManager getManger() {
        return am;
    }


    /**
     * Acquires an arena based on its ID number
     *
     * @param i the ID to search the arenas fir
     * @return the arena possessibg the specified ID
     */
    public Arena getArena(int i) {
        for (Arena a : arenas) {
            if (a.getId() == i) {
                return a;
            }
        }
        return null; // Not found
    }


    /**
     * Adds the player to an arena
     * <p>
     * <p>Gets teh arena by ID, checks that id exist,
     * and check the player isn't already in a game.</p>
     *
     * @param p the player to add
     * @param i th arena ID. A check will be done to ensure its validity
     */
    public void addPlayer(Player p, int i) {
        Arena a = getArena(i);
        if (a == null) {
            p.sendMessage(ChatColor.RED + "Invalid arena!");
            return;
        }


        a.getPlayers().add(p.getName());
        p.teleport(a.spawn);
    }

    public void removePlayer(Player p) {
        Arena a = null;


        // Searches each areba for the player
        for (Arena arena : this.arenas) {
            if (arena.getPlayers().contains(p.getUniqueId()))
                a = arena;
        }

        // Check arena validity
        if (a == null) {
            p.sendMessage(ChatColor.RED + "Invalid operation");
            return;
        }

        a.getPlayers().remove(p.getUniqueId());

        p.setFireTicks(0);
    }

    public Arena createArena(Location l) {
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(l, num);
        arenas.add(a);

        arenaYML.getArenas().set("Arenas." + num, serializeLoc(l));
        List<Integer> list = arenaYML.getArenas().getIntegerList("Arenas.Arenas");
        list.add(num);
        arenaYML.getArenas().set("Arenas.Arenas", list);
        arenaYML.saveArenas();
        arenaYML.reloadArenasFile();

        return a;
    }

    public Arena reloadArena(Location l) {
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(l, num);
        arenas.add(a);

        return a;
    }

    public void removeArena(int i) {
        Arena a = getArena(i);
        if (a == null) {
            return;
        }

        arenas.remove(a);

        arenaYML.getArenas().set("Arenas." + i, null);
        List<Integer> list = arenaYML.getArenas().getIntegerList("Arenas.Arenas");
        list.add(i);
        arenaYML.getArenas().set("Arenas.Arenas", list);
        arenaYML.saveArenas();
        arenaYML.reloadArenasFile();


    }

    public boolean isinGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p.getUniqueId()))
                return true;
        }
        return false;
    }


    public String serializeLoc(Location l) {
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    public Location deserialzeLoc(String s) {
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}
