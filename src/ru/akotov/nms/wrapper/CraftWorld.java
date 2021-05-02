package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;
import org.bukkit.World;

@NmsClass(value = NmsPackage.CB, handle = "CraftWorld")
public interface CraftWorld extends NmsHandler {
    static CraftWorld getInstance(World handle) {
        return NmsClassHelper.getInstance().asProxyInstance(CraftWorld.class, handle);
    }

    @NmsMethod
    WorldServer getHandle();
}
