package community.gamehub.gamehub;

import com.google.gson.Gson;
import community.gamehub.gamehub.commands.Announcement;
import community.gamehub.gamehub.commands.test;
import community.gamehub.gamehub.events.EventTestHandlers;
import community.gamehub.gamehub.util.Person;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public final class Gamehub extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("The Gamehub plugin is working! <3 ");
        // Plugin startup logic
        GetCommands();
        GetEvents();
        Config();
        TestJson();
    }
    @Override
    public void onDisable() {
        getLogger().info("Why did you Disable Gamehub plugin fuck you!");
        // Plugin shutdown logic
    }
    public void GetCommands() {
       // getCommand("test").setExecutor(new test());
        getCommand("Announcement").setExecutor(new Announcement());
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

    }//s

    public void TestJson() {
        Gson gson = new Gson();
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File jsonFile = new File(dataFolder, "person.json");
        try (Writer writer = new FileWriter(jsonFile)) {
            Person person = new Person();
            person.setName("John Smith");
            person.setAge(30);
            person.setEmail("john.smith@example.com");
            gson.toJson(person, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
