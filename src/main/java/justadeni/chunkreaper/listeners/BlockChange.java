package justadeni.chunkreaper.listeners;

import justadeni.chunkreaper.Chunkreaper;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;

public class BlockChange implements Listener {

    @EventHandler
    public void onBlockChange(BlockEvent e){
        Chunk chunk = e.getBlock().getChunk();
        Location loc = new Location(e.getBlock().getWorld(), chunk.getX(), 0, chunk.getZ());
        if (ChunkLoad.isMarked(loc))
            ChunkLoad.removeMarked(loc);
    }

}
