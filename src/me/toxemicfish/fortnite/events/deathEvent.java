package me.toxemicfish.fortnite.events;

import me.toxemicfish.fortnite.Main;
import me.toxemicfish.fortnite.core.Games.gameArena;
import me.toxemicfish.fortnite.utils.chatUtils;
import me.toxemicfish.fortnite.utils.consoleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathEvent implements Listener {

    private Main plugin;

    public deathEvent(Main plugin, String msg) {
        this.plugin = plugin;
        consoleUtils.consoleGood(msg);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = (Player) event.getEntity();
        gameArena gameArena = plugin.getGame();

        event.setDeathMessage("");
        if (gameArena.isAlive(p)) {
            gameArena.sendMessageAlive("&e" + p.getName() + "&a Has just beed killed by &e" + p.getKiller().getName());
            gameArena.removeAlive(p);
            if(gameArena.shouldEnd()) {
                gameArena.endGame();
            }
        }
    }

}
