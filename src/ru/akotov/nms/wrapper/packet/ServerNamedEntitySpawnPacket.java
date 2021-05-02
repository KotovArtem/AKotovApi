package ru.akotov.nms.wrapper.packet;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsField;
import ru.akotov.nms.cglib.NmsClassHelper;
import ru.akotov.nms.wrapper.NmsDataWatcher;
import ru.akotov.nms.wrapper.NmsHandler;
import ru.akotov.nms.wrapper.NmsPacket;

import java.util.UUID;

@NmsClass(value = NmsPackage.NMS, handle = "PacketPlayOutNamedEntitySpawn")
public interface ServerNamedEntitySpawnPacket extends NmsPacket {
    static ServerNamedEntitySpawnPacket create(NmsHandler o) {
        return NmsClassHelper.getInstance().createWithConstructor(
                ServerNamedEntitySpawnPacket.class,
                new Class[]{NmsPackage.NMS.getClass("EntityHuman")},
                o.getNmsHandle()
        );
    }

    @NmsField(NmsField.Type.SETTER)
    void a(int id);

    @NmsField(NmsField.Type.SETTER)
    void b(UUID id);

    @NmsField(NmsField.Type.SETTER)
    void c(double x);

    @NmsField(NmsField.Type.SETTER)
    void d(double y);

    @NmsField(NmsField.Type.SETTER)
    void e(int z);

    @NmsField(NmsField.Type.SETTER)
    void c(int x);

    @NmsField(NmsField.Type.SETTER)
    void d(int y);

    @NmsField(NmsField.Type.SETTER)
    void e(double z);

    @NmsField(NmsField.Type.SETTER)
    void h(int item);

    @NmsField(NmsField.Type.SETTER)
    void i(NmsDataWatcher watcher);
}
