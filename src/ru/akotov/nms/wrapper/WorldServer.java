package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;

@NmsClass(value = NmsPackage.NMS, handle = "WorldServer")
public interface WorldServer extends World {
    @NmsMethod
    boolean addEntity(Entity entity);
}
