package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;

@NmsClass(value = NmsPackage.NMS, handle = "PlayerConnection")
public interface NmsPlayerConnection extends NmsHandler {
    @NmsMethod
    void sendPacket(NmsPacket packet);
}
