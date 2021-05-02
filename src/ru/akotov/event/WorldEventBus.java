package ru.akotov.event;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import org.bukkit.event.weather.WeatherEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.plugin.Plugin;

public class WorldEventBus extends FilteredEventBus<World> {
    public WorldEventBus(Plugin plugin) {
        super(plugin);
    }

    protected THandler<? extends Event, World> createHandler(Class<? extends Event> eventClass) {
        // World events
        if (WeatherEvent.class.isAssignableFrom(eventClass))
            return new THandler<>(WeatherEvent::getWorld);
        if (WorldEvent.class.isAssignableFrom(eventClass))
            return new THandler<>(WorldEvent::getWorld);
        if (BlockEvent.class.isAssignableFrom(eventClass))
            return new THandler<BlockEvent, World>(event -> event.getBlock().getWorld());

        // Entity events
        if (EntityEvent.class.isAssignableFrom(eventClass))
            return new THandler<EntityEvent, World>(event -> event.getEntity().getWorld());
        if (HangingEvent.class.isAssignableFrom(eventClass))
            return new THandler<HangingEvent, World>(event -> event.getEntity().getWorld());
        if (VehicleEvent.class.isAssignableFrom(eventClass))
            return new THandler<VehicleEvent, World>(event -> event.getVehicle().getWorld());

        // Inventory events
        if (InventoryPickupItemEvent.class.isAssignableFrom(eventClass))
            return new THandler<InventoryPickupItemEvent, World>(event -> event.getItem().getWorld());
        if (InventoryEvent.class.isAssignableFrom(eventClass))
            return new THandler<InventoryEvent, World>(event -> event.getView().getPlayer().getWorld());

        // Player events
        if (PlayerEvent.class.isAssignableFrom(eventClass))
            return new THandler<PlayerEvent, World>(event -> event.getPlayer().getWorld());
        if (PlayerLeashEntityEvent.class.isAssignableFrom(eventClass))
            return new THandler<PlayerLeashEntityEvent, World>(event -> event.getPlayer().getWorld());

        // Custom events
        if (WorldGetterEvent.class.isAssignableFrom(eventClass))
            return new THandler<>(WorldGetterEvent::getWorld);

        throw new NullPointerException("Could not create THandler for " + eventClass.getName());
    }

}
