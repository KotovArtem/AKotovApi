package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "Packet")
public interface NmsPacket extends NmsHandler {
    static NmsPacket fromNms(Object nmsPacket) {
        return NmsClassHelper.getInstance().asProxyInstance(NmsPacket.class, nmsPacket);
    }
}
