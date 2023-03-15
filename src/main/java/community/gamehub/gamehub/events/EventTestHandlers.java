package community.gamehub.gamehub.events;

import community.gamehub.gamehub.Gamehub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventTestHandlers implements Listener {
    public EventTestHandlers(Gamehub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTorchPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        String blockTypeName = block.getType().toString();
        String playerName = event.getPlayer().getName();
        String message =  " placed a " + blockTypeName + " block!";
        Bukkit.getServer().broadcastMessage(message);
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendTitle(playerName, message, 10, 70, 20);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        // Get the block below the player's feet
        Location blockLocation = new Location(location.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
        Material blockType = blockLocation.getBlock().getType();

        // If the block below the player's feet is air or water, spawn a block
        if (blockType == Material.AIR || blockType == Material.WATER) {
            blockLocation.getBlock().setType(Material.DIAMOND_BLOCK);
        }
    }
}
