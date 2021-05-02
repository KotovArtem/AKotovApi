package ru.akotov.plugin;

import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;
import ru.akotov.listener.WorldListener;

public final class AKotovPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getLogger().info("AKotovPlugin enabled");
    }
}

