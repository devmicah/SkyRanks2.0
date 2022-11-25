package dev.micah.skyranks;

import dev.micah.skyranks.commands.SkyRanksCommand;
import dev.micah.skyranks.io.StoredData;
import dev.micah.skyranks.io.YamlFile;
import dev.micah.skyranks.listeners.ChatListener;
import dev.micah.skyranks.listeners.GuiListener;
import dev.micah.skyranks.listeners.UserLogListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class SkyRanks extends JavaPlugin {

    // Main plugin instance
    private static SkyRanks PLUGIN_INSTANCE;

    // Initialize variable to access ranks file
    private YamlFile ranksFile;
    private YamlFile playerFile;

    @Override
    public void onEnable() {
        // Check if data folder exists
        if (!getDataFolder().exists()) getDataFolder().mkdirs();

        // Sets the plugins instance
        PLUGIN_INSTANCE = this;

        // Creates stored variables
        new StoredData();

        // Define (also create if needed) and get the ranks/players file.
        this.ranksFile = new YamlFile("ranks", getDataFolder());
        this.playerFile = new YamlFile("players", getDataFolder());

        // Register listeners
        List<Listener> listeners = Arrays.asList(
                //new ChatListener(),
                new GuiListener(),
                new UserLogListener()
        );
        listeners.forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        // Register commands
        getCommand("skyranks").setExecutor(new SkyRanksCommand());
    }

    @Override
    public void onDisable() {
    }


    /**
     * Static variable giving java plugin instance
     * @return plugins instance
     */
    public static SkyRanks getPluginInstance() {
        return PLUGIN_INSTANCE;
    }

    /**
     * Ranks file variable, all rank data is saved here
     * @return ranks file (custom class)
     */
    public YamlFile getRanksFile() {
        return ranksFile;
    }

    /**
     * Players file variable, all player data is saved here
     * @return players file (custom class)
     */
    public YamlFile getPlayerFile() {
        return playerFile;
    }
}
