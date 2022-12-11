package dev.micah.skyranks.file;

import dev.micah.skyranks.SkyRanks;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileYaml {

    private SkyRanks plugin = SkyRanks.getPluginInstance();

    @Getter
    private File file;
    @Getter
    private File directory;
    @Getter
    private YamlConfiguration config;

    public FileYaml(String fileName, File directory) {
        this(new File(directory, (fileName.split("\\.").length >= 1 ? fileName.split("\\.")[0] : fileName) + ".yml"));
    }

    public FileYaml(File file) {
        if (file == null || file.isDirectory()) { Bukkit.getLogger().severe("[SkyRanks] Failed to load a configuration file!"); return; }
        if (!file.exists()) {
            try {
                file.createNewFile(); } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        this.file = file;
        this.directory = plugin.getDataFolder();
    }

    public void save() {
        try {
            config.save(file); } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
