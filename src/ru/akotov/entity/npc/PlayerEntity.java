package ru.akotov.entity.npc;

import org.bukkit.entity.Player;

public interface PlayerEntity {
    void send(Player player);

    String getName();

    int getId();
}

