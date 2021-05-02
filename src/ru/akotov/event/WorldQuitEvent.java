package ru.akotov.event;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;

public class WorldQuitEvent extends WorldEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public WorldQuitEvent(Player player, World world) {
        super(world);
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }
}

