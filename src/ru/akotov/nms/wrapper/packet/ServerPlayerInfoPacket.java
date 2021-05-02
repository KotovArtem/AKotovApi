package ru.akotov.nms.wrapper.packet;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.cglib.NmsClassHelper;
import ru.akotov.nms.wrapper.EnumPlayerInfoAction;
import ru.akotov.nms.wrapper.NmsHandler;
import ru.akotov.nms.wrapper.NmsPacket;

import java.lang.reflect.Array;

@NmsClass(value = NmsPackage.NMS, handle = "PacketPlayOutPlayerInfo")
public interface ServerPlayerInfoPacket extends NmsPacket {
    static ServerPlayerInfoPacket create(EnumPlayerInfoAction action, NmsHandler o) {
        return NmsClassHelper.getInstance().createWithConstructor(
                ServerPlayerInfoPacket.class,
                new Class[]{
                        NmsPackage.NMS.getClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction"),
                        Array.newInstance(NmsPackage.NMS.getClass("EntityPlayer"), 0).getClass()
                },
                action.getNmsHandle(),
                o.getNmsHandle()
        );
    }
}
