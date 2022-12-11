package dev.micah.skyranks.handler;

import dev.micah.skyranks.SkyRanks;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerHandler {

    public static class Storage {

        @Getter
        List<PlayerHandler> concurrentData = new ArrayList<>();

        public void addPlayer(PlayerHandler player) {
            if (concurrentData.contains(player)) return;
            concurrentData.add(player);
        }

        public void removePlayer(PlayerHandler player) {
            concurrentData.remove(player);
        }

        public PlayerHandler findPlayer(Player player) {
            AtomicReference<PlayerHandler> result = new AtomicReference<>();
            concurrentData.forEach(playerHandler -> {
                if (playerHandler.getPlayer() == player) {
                    result.set(playerHandler);
                }
            });
            return result.get();
        }

    }

    @Getter
    private String rank;
    @Getter
    private Player player;

    private YamlConfiguration config = SkyRanks.getPluginInstance().getPlayerFile().getConfig();
    private String path;


    public PlayerHandler(Player player) {
        this.player = player;
        this.path = "players." + player.getUniqueId() + ".";

        List<String> users = config.getStringList("registered-users");
        if (!isPlayerRegistered(users)) {
            users.add(player.getUniqueId().toString());
            setupPlayerData(path, users);
        }

        this.rank = config.getString(path + "rank");
    }

    public boolean isPlayerRegistered(List<String> users) {
        return users.contains(player.getUniqueId().toString());
    }

    public void setupPlayerData(String path, List<String> users) {
        config.set(path + "rank", "");
        config.set("registered-users", users);
        savePlayers();
    }

    private void savePlayers() {
        SkyRanks.getPluginInstance().getPlayerFile().save();
    }

}
