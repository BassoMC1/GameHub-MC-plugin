package community.gamehub.gamehub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class RankAddUser implements CommandExecutor {

    private Plugin plugin;

    public RankAddUser(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (args.length >= 2) {
                    String arg1 = args[0];
                    String arg2 = args[1];

                    FileWriter writer;
                    File file = new File(plugin.getDataFolder(), "filename.txt");
                    Player playerCheck = Bukkit.getPlayer(arg1);
                    if(playerCheck == null) {
                        player.sendMessage("Can't find that username");
                        return false;
                    }

                    try {
                        @NotNull UUID uuid = getUUID(arg1);
                        writer = new FileWriter(file, true);

                        writer.write("username>UUID>Rank," + arg1 + ">" + uuid + ">" + arg2 +"\n");
                        writer.close();

                        String data;

                        Scanner myReader = new Scanner(file);
                        while (myReader.hasNextLine()) {
                            data = myReader.nextLine();
                            if (Objects.equals(data, "#Imporntent This is the DataBase for Gamehub Ranks")) {
                                //do noting
                            } else if (Objects.equals(data, "#DO not remove ore chance any ting in this file.")) {
                                //do noting
                            } else {
                                String[] lineArray = data.split(",");

                                String key = lineArray[0];
                                String value = lineArray[1];

                                String[] keys = key.split(">");
                                String[] values = value.split(">");

                                // Map<String, String> user = new HashMap<>();
                                for (int i = 0; i < keys.length; i++) {
                                    getLogger().info(keys[i] + ":" + values[i]);
                                }
                            }

                        }
                        myReader.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    player.sendMessage("Usage: /RankAddUser <Username> <Rank>");
                    return false;
                }

            }
        }
        return true;
    }
    public @NotNull UUID getUUID(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        return player.getUniqueId();
    }

}
