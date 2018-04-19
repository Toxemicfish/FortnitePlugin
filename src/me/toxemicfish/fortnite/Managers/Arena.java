package me.toxemicfish.fortnite.Managers;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    int id;
    public Location spawn;
    List<String> players = new ArrayList<String>();

    public Arena(Location spawn, int id) {
        this.spawn = spawn;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<String> getPlayers() {
        return players;
    }
}