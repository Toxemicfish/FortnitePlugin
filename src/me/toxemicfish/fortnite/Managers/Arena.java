package me.toxemicfish.fortnite.Managers;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    private final int id;
    private final Location spawn;
    private final List<UUID> players = new ArrayList<UUID>();

    public Arena(Location spawn, int id) {
        this.spawn = spawn;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Location getSpawn() {
        return spawn;
    }

    public List<UUID> getPlayers() {
        return players;
    }
}
