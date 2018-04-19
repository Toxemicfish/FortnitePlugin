package me.toxemicfish.fortnite.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ArenaManager {

    // Singleton instance
    private static ArenaManager am;

    // Player data
    private final Map<UUID, Location> locs = new HashMap<UUID, Location>();
    private final Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
    private final Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();

    private final List<Arena> arenas = new ArrayList<Arena>();

    // Keeps track of the current arena ID
    private int arenaSize = 0;

    private ArenaManager() {
    }

    public static ArenaManager getManger() {
        if (am == null)
            am = new ArenaManager();
        return am;
    }


    /**
     * Acquires an arena based on its ID number
     *
     * @param i the ID to search the arenas fir
     * @return the arena possessibg the specified ID
     */
    public Arena getArena(int i) {
        for (Arena a : this.arenas) {
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
        Arena a = this.getArena(i);
        if (a == null) {
            p.sendMessage(ChatColor.RED + "Invalid arena!");
            return;
        }

        if (this.isinGame(p)) {
            p.sendMessage(ChatColor.RED + "Cannot join more than 1 game");
            return;
        }
        a.getPlayers().add(p.getUniqueId());
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
        this.arenaSize++;

        Arena a = new Arena(l, this.arenaSize);
        this.arenas.add(a);

        return a;
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
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]),  Integer.parseInt(st[2]),  Integer.parseInt(st[3]));
    }
}
