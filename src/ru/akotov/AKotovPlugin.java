package ru.akotov;

import org.bukkit.plugin.java.JavaPlugin;
import ru.akotov.service.AKotovApi;

public abstract class AKotovPlugin extends JavaPlugin {

    @Override
    public final void onEnable() {
        onPluginEnable(getServer().getServicesManager().getRegistration(AKotovApi.class).getProvider());
    }

    protected abstract void onPluginEnable(AKotovApi api);


}
