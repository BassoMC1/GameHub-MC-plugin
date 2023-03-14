package community.gamehub.gamehub.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                p.sendMessage(Component.text(( "You have successfully jumped.")).color(TextColor.color(0, 255, 100)));
                p.setVelocity(new Vector(0.0, 10.0, 0.0));
            } else {
                p.sendMessage(Component.text(( "You do not have permission to use this command.")).color(TextColor.color(255,0,0)));
            }
        }
        return true;
    }
}
