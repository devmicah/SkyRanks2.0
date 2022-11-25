package dev.micah.skyranks.listeners;

import dev.micah.skyranks.handler.PlayerHandler;
import dev.micah.skyranks.io.StoredData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserLogListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        StoredData.getPlayerHandlers().add(new PlayerHandler(event.getPlayer()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (StoredData.getPlayerHandlers().isEmpty()) return;
        for (PlayerHandler handler : StoredData.getPlayerHandlers()) {
            if (handler.getPlayer() == event.getPlayer()) {
                StoredData.getPlayerHandlers().remove(handler);
                return;
            }
        }
    }

}
