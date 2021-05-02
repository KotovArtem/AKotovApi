package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "PlayerInteractManager")
public interface PlayerInteractManager extends NmsHandler {
    static PlayerInteractManager create(WorldServer object) {
        return NmsClassHelper.getInstance().createWithConstructor(
                PlayerInteractManager.class,
                new Class[]{NmsPackage.NMS.getClass("World")},
                object.getNmsHandle()
        );
    }
}
