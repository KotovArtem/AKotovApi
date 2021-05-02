package ru.akotov.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.akotov.event.WorldQuitEvent;

public final class WorldListener implements Listener {
    @EventHandler
    public void on(PlayerTeleportEvent event) {
        World from = event.getFrom().getWorld();
        World to = event.getTo().getWorld();
        if (from.equals(to)) return;
        Bukkit.getPluginManager().callEvent(new WorldQuitEvent(event.getPlayer(), from));
    }
}
