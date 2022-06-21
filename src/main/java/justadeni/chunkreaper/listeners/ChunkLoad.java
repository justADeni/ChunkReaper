package justadeni.chunkreaper.listeners;

import justadeni.chunkreaper.configs.ConfigsUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.ArrayList;

public class ChunkLoad implements Listener {

    private static ArrayList<Location> markedForDeletion = new ArrayList<>();

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e){
        if (e.isNewChunk()) {

            int X = e.getChunk().getX();
            int Z = e.getChunk().getZ();
            String world = e.getChunk().getWorld().getName();

            if (!ConfigsUtils.getIgnoreWorlds().contains(world)) {
                if (ConfigsUtils.isInsideRadius(X, Z, world)) {
                    if (!ConfigsUtils.isInsideRegion(X, Z, world)) {
                        Location loc = new Location(e.getWorld(), X, 0, Z);
                        markedForDeletion.add(loc);
                    }
                }
            }
        }
    }

    //public static void addMarked(Location loc){markedForDeletion.add(loc);}
    public static boolean isMarked(Location loc){
        return markedForDeletion.contains(loc);
    }

    public static void removeMarked(Location loc){
        markedForDeletion.remove(loc);
    }

}
