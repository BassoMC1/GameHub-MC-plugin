package community.gamehub.gamehub;

import community.gamehub.gamehub.commands.test;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gamehub extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("The Gamehub plugin is working! <3 ");
        // Plugin startup logic
        GetCommands();
    }
    @Override
    public void onDisable() {
        getLogger().info("Why did you Disable Gamehub plugin fuck you!");
        // Plugin shutdown logic
    }
    public void GetCommands() {
        getCommand("test").setExecutor(new test());
    }
}
