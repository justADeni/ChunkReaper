package justadeni.chunkreaper.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Chunk chunk = e.getBlock().getChunk();
        Location loc = new Location(e.getBlock().getWorld(), chunk.getX(), 0, chunk.getZ());
        if (ChunkLoad.isMarked(loc))
            ChunkLoad.removeMarked(loc);
    }

}
