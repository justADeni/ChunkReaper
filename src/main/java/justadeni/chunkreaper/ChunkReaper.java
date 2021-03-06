package justadeni.chunkreaper;

import justadeni.chunkreaper.configs.ChunkFile;
import justadeni.chunkreaper.listeners.BlockBreak;
import justadeni.chunkreaper.listeners.BlockPlace;
import justadeni.chunkreaper.listeners.ChunkLoad;
import justadeni.chunkreaper.listeners.ChunkUnload;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class ChunkReaper extends JavaPlugin {

    public static Plugin instance;
    public ChunkFile config;
    @Override
    public void onEnable() {
        instance = this;
        config = new ChunkFile(this);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
        getServer().getPluginManager().registerEvents(new ChunkUnload(), this);
        getCommand("ChunkReaper").setExecutor(new ChunkCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
        try {
            config.saveData();
            saveConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
