package justadeni.chunkreaper;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChunkFile {
    private Chunkreaper plugin;
    private File dataFile = null;
    private FileConfiguration fileConfiguration = null;

    public ChunkFile(Chunkreaper plugin){
        this.plugin = plugin;
        saveDefaultData();
    }

    public void reloadData(){
        if (dataFile == null){
            dataFile = new File(plugin.getDataFolder(), "data.yml");
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(dataFile);

        InputStream defaultStream = plugin.getResource("data.yml");
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
        if (dataFile == null || fileConfiguration == null)
            return;

        getData().save(dataFile);
    }

    public void saveDefaultData(){
        if (dataFile == null)
            dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!dataFile.exists())
            plugin.saveResource("data.yml", false);
    }
}
