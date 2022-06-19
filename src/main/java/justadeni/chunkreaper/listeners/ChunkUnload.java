package justadeni.chunkreaper.listeners;

import justadeni.chunkreaper.Chunkreaper;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnload implements Listener {

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e){
        Location loc = new Location(e.getWorld(), e.getChunk().getX(), 0, e.getChunk().getZ());
        if (ChunkLoad.isMarked(loc)){
            e.getChunk().unload(false);
            //Chunkreaper.data.getData().set("airchunks.", loc);
            ChunkLoad.removeMarked(loc);
        }
    }
}
