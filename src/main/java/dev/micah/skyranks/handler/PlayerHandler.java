package dev.micah.skyranks.handler;

import dev.micah.skyranks.SkyRanks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerHandler {

    private YamlConfiguration config = SkyRanks.getPluginInstance().getPlayerFile().getConfig();
    private String path;
    private String rank;
    private Player player;

    public PlayerHandler(Player player) {
        this.path = "players." + player.getUniqueId() + ".";
        List<String> registeredUsers = config.getStringList("registered-users");
        if (!registeredUsers.contains(player.getUniqueId().toString())) {
            registeredUsers.add(player.getUniqueId().toString());
            config.set(path + "rank", "");
            config.set("registered-users", registeredUsers);
            savePlayers();
        }
        this.rank = config.getString(path + "rank");
        this.player = player;
    }

    private void savePlayers() {
        SkyRanks.getPluginInstance().getPlayerFile().save();
    }

    public String getRank() {
        return rank;
    }

    public Player getPlayer() {
        return player;
    }

}
