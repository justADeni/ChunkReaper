package justadeni.chunkreaper.configs;

import justadeni.chunkreaper.ChunkReaper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigsUtils {

    private static ChunkReaper chunkReaper = new ChunkReaper();
    private static FileConfiguration mainConfig(){return chunkReaper.getConfig();}
    private static FileConfiguration regionConfig(){return chunkReaper.config.getData();}
    //private static ChunkFile regionConfigData(){return chunkReaper.config;}

    public static void save(){
        try {
            chunkReaper.saveConfig();
            chunkReaper.config.saveData();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void reload(){
        chunkReaper.reloadConfig();
        chunkReaper.config.reloadData();
    }

    public static List<String> getIgnoreWorlds(){
        ArrayList<String> worlds = new ArrayList<>(mainConfig().getStringList("IgnoreWorlds"));
        return worlds;
    }

    public static boolean isInsideRadius(int X, int Z, String worldname){

        int radius = mainConfig().getInt("Radius");
        if (radius <= 0)
            return false;

        World world = Bukkit.getWorld(worldname);
        //int chunkDist = (radius/2) >> 4;

        if ((new Location(world, X << 4, 0, Z << 4).distance(world.getSpawnLocation())) < ((double) radius/2)){
            return true;
        }
        return false;
    }
    public static boolean isInsideRegion(int X, int Z, String worldname){
        if (mainConfig().getBoolean("Regions")) {

            ConfigurationSection sec = regionConfig().getConfigurationSection("data");
            for (String key : sec.getKeys(false)) {
                String world = regionConfig().getString("data." + key + ".world");
                if (!Objects.equals(world, worldname))
                    continue;

                int x1 = regionConfig().getInt("data." + key + ".x1");
                int z1 = regionConfig().getInt("data." + key + ".z1");
                int x2 = regionConfig().getInt("data." + key + ".x2");
                int z2 = regionConfig().getInt("data." + key + ".z2");


                //see https://stackoverflow.com/a/25120739
                int m = (x1 + x2) / 2;
                if (Math.abs(X - m) <= (Math.abs(x1 - m))) {
                    int n = (z1 + z2) / 2;
                    if (Math.abs(Z - n) <= (Math.abs(z1 - n))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
