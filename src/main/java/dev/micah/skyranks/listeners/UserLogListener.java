package dev.micah.skyranks.listeners;

import dev.micah.skyranks.handler.PlayerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserLogListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new PlayerHandler.Storage().addPlayer(new PlayerHandler(event.getPlayer()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        PlayerHandler.Storage players = new PlayerHandler.Storage();
        if (players.getConcurrentData().isEmpty()) return;
        players.removePlayer(players.findPlayer(event.getPlayer()));
    }

}
