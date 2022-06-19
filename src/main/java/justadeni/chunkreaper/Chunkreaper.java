package justadeni.chunkreaper;

import justadeni.chunkreaper.listeners.BlockChange;
import justadeni.chunkreaper.listeners.ChunkLoad;
import justadeni.chunkreaper.listeners.ChunkUnload;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chunkreaper extends JavaPlugin {

    public static Plugin instance;
    //public static ChunkFile data;
    @Override
    public void onEnable() {
        instance = this;
        //data = new ChunkFile(this);
        getServer().getPluginManager().registerEvents(new BlockChange(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
        getServer().getPluginManager().registerEvents(new ChunkUnload(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
