package dev.micah.skyranks.listeners;

import dev.micah.skyranks.handler.PlayerHandler;
import dev.micah.skyranks.ranks.Ranks;
import dev.micah.skyranks.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.atomic.AtomicReference;

public class ChatListener implements Listener {

    @EventHandler
    // TODO CHAT HANDLER
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        String msgContent = event.getMessage();
        AtomicReference<PlayerHandler> handler = null;

        Ranks rank = new Ranks(null, handler.get().getRank());

        Bukkit.broadcastMessage(Chat.color(""));

    }

}
