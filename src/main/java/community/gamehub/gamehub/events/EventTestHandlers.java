package community.gamehub.gamehub.events;

import community.gamehub.gamehub.Gamehub;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventTestHandlers implements Listener {
    public EventTestHandlers(Gamehub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTorchPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        String blockTypeName = block.getType().toString();
        String playerName = event.getPlayer().getName();
        String message = playerName + " placed a " + blockTypeName + " block!";
        Bukkit.getServer().broadcastMessage(message);



    }
}
