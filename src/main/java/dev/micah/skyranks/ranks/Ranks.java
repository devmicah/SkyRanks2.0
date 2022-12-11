package dev.micah.skyranks.ranks;

import dev.micah.skyranks.SkyRanks;
import dev.micah.skyranks.util.Chat;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Ranks {

    public static List<String> getRanks() {
        return config.getStringList("ranks.registered");
    }
    @Getter
    private static final YamlConfiguration config = SkyRanks.getPluginInstance().getRanksFile().getConfig();
    @Getter
    private String rankIdentifier;
    @Getter
    private Player player;
    private String path;

    public Ranks(Player cmdExecutor, String rankIdentifier) {
        this.rankIdentifier = rankIdentifier;
        this.player = cmdExecutor;
        this.path = "ranks." + rankIdentifier + ".";
    }

    private void saveRanks() {
        SkyRanks.getPluginInstance().getRanksFile().save();
    }

    public boolean verifyColorCode(String code) {
        if (code.charAt(0) == '&' && code.length() % 2 == 0) {
            char[] valid = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'k', 'l', 'm', 'n', 'o', 'r'};
            String[] codesSplit = code.split("&");
            for (String cd :
                    codesSplit) {
                if (cd.length() == 1) {
                    for (char v : valid) {
                        if (cd.charAt(0) == v) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean build() {
        if (!exists()) {
            List<String> ranks = config.getStringList("ranks.registered");
            ranks.add(rankIdentifier);
            config.set("ranks.registered", ranks);
            config.set(path + "prefix", "");
            config.set(path + "suffix", "");
            config.set(path + "name-color", "");
            config.set(path + "chat-color", "");
            config.set(path + "nickable", false);
            config.set(path + "default", false);
            saveRanks();
            return true;
        }
        return false;
    }
    
    private boolean exists() {
        return config.getStringList("ranks.registered").contains(rankIdentifier);
    }

    public boolean isDefault() {
        return config.getBoolean(path + "default");
    }

    public boolean isNickable() {
        return config.getBoolean(path + "nickable");
    }

    public String getPrefix() {
        return config.getString(path + "prefix");
    }

    public String getSuffix() {
        return config.getString(path + "suffix");
    }

    public String getNameColor() {
        return config.getString(path + "name-color");
    }

    public String getChatColor() {
        return config.getString(path + "chat-color");
    }

    public Ranks setDefault() {
        for (String r : getRanks()) {
            if (!r.equals(getRankIdentifier())) {
                config.set("ranks." + r + ".default", false);
            }
        }
        config.set(path + "default", true);
        saveRanks();
        return this;
    }

    public Ranks setNickable(boolean nickable) {
        config.set(path + "nickable", nickable);
        saveRanks();
        return this;
    }

    public Ranks setSuffix(String suffix) {
        config.set(path + "suffix", suffix);
        saveRanks();
        return this;
    }

    public Ranks setPrefix(String prefix) {
        config.set(path + "prefix", prefix);
        saveRanks();
        return this;
    }

    public Ranks setChatColor(String colorCode) {
        if (verifyColorCode(colorCode)) {
            config.set(path + "chat-color", colorCode);
            saveRanks();
        } else {
            player.sendMessage(Chat.color("&b[SkyRanks] &rThe color code you specified is invalid! &c(" + colorCode + ")"));
        }
        return this;
    }

    public Ranks setNameColor(String colorCode) {
        if (verifyColorCode(colorCode)) {
            config.set(path + "name-color", colorCode);
            saveRanks();
        } else {
            player.sendMessage(Chat.color("&b[SkyRanks] &rThe color code you specified is invalid! &c(" + colorCode + ")"));
        }
        return this;
    }

    public void destruct() {
        List<String> ranks = getRanks();
        ranks.remove(rankIdentifier);
        config.set("ranks.registered", ranks);
        config.set("ranks." + rankIdentifier, null);
        saveRanks();
    }

}
