package dev.micah.skyranks;

import dev.micah.skyranks.commands.SkyRanksCommand;
import dev.micah.skyranks.file.FileYaml;
import dev.micah.skyranks.listeners.GuiListener;
import dev.micah.skyranks.listeners.UserLogListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class SkyRanks extends JavaPlugin {

    @Getter
    private static SkyRanks pluginInstance;
    @Getter
    private FileYaml ranksFile;
    @Getter
    private FileYaml playerFile;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();

        pluginInstance = this;

        this.ranksFile = new FileYaml("ranks", getDataFolder());
        this.playerFile = new FileYaml("players", getDataFolder());

        List<Listener> listeners = Arrays.asList(
                //new ChatListener(),
                new GuiListener(),
                new UserLogListener()
        );
        listeners.forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        getCommand("skyranks").setExecutor(new SkyRanksCommand());
    }

    @Override
    public void onDisable() {

    }

}
