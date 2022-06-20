package justadeni.chunkreaper.listeners;

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
            Location loc = new Location(e.getWorld(), e.getChunk().getX(), 0, e.getChunk().getZ());
        /*
        if (Chunkreaper.data.getData().contains("airchunks." + loc)) {
            e.getWorld().regenerateChunk(e.getChunk().getX(), e.getChunk().getZ());
        }
        */
            markedForDeletion.add(loc);
        }
    }

    public static boolean isMarked(Location loc){
        return markedForDeletion.contains(loc);
    }

    public static void removeMarked(Location loc){
        markedForDeletion.remove(loc);
    }

}
