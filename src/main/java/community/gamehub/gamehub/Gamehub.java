package community.gamehub.gamehub;

import community.gamehub.gamehub.commands.Announcement;
import community.gamehub.gamehub.commands.RankAddUser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Gamehub extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("The Gamehub plugin is working! <3 ");
        // Plugin startup logic
        GetCommands();
        GetEvents();
        Config();
        TestDbTextFile();
    }
    @Override
    public void onDisable() {
        getLogger().info("Why did you Disable Gamehub plugin fuck you!");
        // Plugin shutdown logic
    }
    public void GetCommands() {
       // getCommand("test").setExecutor(new test());
        getCommand("Announcement").setExecutor(new Announcement());
        getCommand("RankAddUser").setExecutor(new RankAddUser(this));
    }

    public void GetEvents() {
        // new EventTestHandlers(this);
        getLogger().info("Events is Working");

    }

    public void Config() {
        File configFile = new File(getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.options().copyDefaults(true); // copy missing keys from the default configuration
        config.addDefault("AnnouncementColor", "255, 255, 255");
        config.addDefault("messageColor", "255, 255, 255");
       // AnnouncementColor = [server Announcement];
       // messageColor = The message that u send;
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void TestDbTextFile() {
        try {
            File file = new File(getDataFolder(), "Rank.txt");
            if (file.createNewFile()) {
                getLogger().info("File created: " + file.getName());
                FileWriter writer = new FileWriter(file, true);
                writer.write("#Imporntent This is the DataBase for Gamehub Ranks\n#DO not remove ore chance any ting in this file.\n");
                writer.close();
            } else {
                getLogger().info("File already exists.");
            }

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
        } catch (FileNotFoundException e) {
            getLogger().info("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
