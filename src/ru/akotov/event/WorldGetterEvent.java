package ru.akotov.event;

import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.function.Supplier;

public abstract class WorldGetterEvent extends Event {
    private final Supplier<World> getter;
    private final boolean cacheWorld;
    private World world;

    protected WorldGetterEvent(Supplier<World> getter, boolean cacheWorld) {
        this.getter = getter;
        this.cacheWorld = cacheWorld;
    }

    World getWorld() {
        return cacheWorld ? world == null ? world = getter.get() : world : getter.get();
    }
}

