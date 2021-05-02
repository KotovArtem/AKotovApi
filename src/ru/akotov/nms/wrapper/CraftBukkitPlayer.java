package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;
import org.bukkit.entity.Player;

@NmsClass(value = NmsPackage.CB_ENTITY, handle = "CraftPlayer")
public interface CraftBukkitPlayer extends NmsHandler {
    static CraftBukkitPlayer get(Player player) {
        return NmsClassHelper.getInstance().asProxyInstance(CraftBukkitPlayer.class, player);
    }

    @NmsMethod
    NmsPlayer getHandle();
}
