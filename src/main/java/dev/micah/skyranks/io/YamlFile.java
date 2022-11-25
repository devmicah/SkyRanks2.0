package dev.micah.skyranks.io;

import dev.micah.skyranks.SkyRanks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlFile {

    private SkyRanks plugin = SkyRanks.getPluginInstance();

    private File file;
    private File directory;
    private YamlConfiguration config;

    public YamlFile(String fileName, File directory) {
        this(new File(directory, (fileName.split("\\.").length >= 1 ? fileName.split("\\.")[0] : fileName) + ".yml"));
    }

    public YamlFile(File file) {
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

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public File getDirectory() {
        return directory;
    }

    public void save() {
        try {
            config.save(file); } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
