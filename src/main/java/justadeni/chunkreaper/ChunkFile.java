package justadeni.chunkreaper;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChunkFile {
    private ChunkReaper plugin;
    private File configFile = null;
    private FileConfiguration fileConfiguration = null;

    public ChunkFile(ChunkReaper plugin){
        this.plugin = plugin;
        saveDefaultData();
    }

    public void reloadData(){
        if (configFile == null){
            configFile = new File(plugin.getDataFolder(), "regions.yml");
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        InputStream defaultStream = plugin.getResource("regions.yml");
        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            fileConfiguration.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getData(){
        if (fileConfiguration == null)
            reloadData();

        return fileConfiguration;
    }

    public void saveData() throws IOException {
        if (configFile == null || fileConfiguration == null)
            return;

        getData().save(configFile);
    }

    public void saveDefaultData(){
        if (configFile == null)
            configFile = new File(plugin.getDataFolder(), "regions.yml");

        if (!configFile.exists())
            plugin.saveResource("regions.yml", false);
    }
}
