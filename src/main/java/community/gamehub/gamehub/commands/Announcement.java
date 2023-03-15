package community.gamehub.gamehub.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Announcement implements CommandExecutor {
    JavaPlugin plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Gamehub");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.isOp()) {
                File configFile = new File(plugin.getDataFolder(), "config.yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

                String colorStringAnnouncement = config.getString("AnnouncementColor");
                String[] colorValues = colorStringAnnouncement.split(",\\s*"); // split on commas with optional whitespace
                int x = Integer.parseInt(colorValues[0]);
                int y = Integer.parseInt(colorValues[1]);
                int z = Integer.parseInt(colorValues[2]);
                TextColor colorAnnouncement = TextColor.color(x, y, z);


                String colorStringMessage = config.getString("messageColor");
                String[] colorValuesMessage = colorStringMessage.split(",\\s*"); // split on commas with optional whitespace
                int xx = Integer.parseInt(colorValuesMessage[0]);
                int yy = Integer.parseInt(colorValuesMessage[1]);
                int zz = Integer.parseInt(colorValuesMessage[2]);
                TextColor colorMessage = TextColor.color(xx, yy, zz);




                player.sendMessage(Component.text(( "The message is now send to all people on the server")).color(TextColor.color(0, 255, 100)));

                String message = String.join(" ", args);
                Component announcement = Component.text("[Server Announcement] ")
                        .color(TextColor.color(colorAnnouncement))
                        .append(Component.text(message)
                                .color(TextColor.color(colorMessage)));
                Bukkit.getServer().broadcast(announcement);


                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.sendTitle("Announcement", "Look in the chat to see it <3", 10, 70, 20);

                }
            } else {
                player.sendMessage(Component.text(( "You do not have permission to use this command.")).color(TextColor.color(255,0,0)));

            }
        }
        return false;
    }
}
