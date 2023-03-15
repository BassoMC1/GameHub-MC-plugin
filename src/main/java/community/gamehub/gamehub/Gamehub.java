package community.gamehub.gamehub;

import community.gamehub.gamehub.commands.Announcement;
import community.gamehub.gamehub.commands.test;
import community.gamehub.gamehub.events.EventTestHandlers;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class Gamehub extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("The Gamehub plugin is working! <3 ");
        // Plugin startup logic
        GetCommands();
        GetEvents();
        Config();
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
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
