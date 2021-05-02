package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import org.bukkit.Location;

@NmsClass(value = NmsPackage.NMS, handle = "Entity")
public interface Entity extends NmsHandler {
    @NmsMethod
    void setLocation(double d0, double d1, double d2, float f, float f1);

    @NmsMethod
    void teleportTo(Location l, boolean b);

    @NmsMethod
    int getId();
}
