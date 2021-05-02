package ru.akotov.nms.wrapper;

import com.mojang.authlib.GameProfile;
import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "EntityPlayer")
public interface EntityPlayer extends Entity {
    static EntityPlayer create(
            MinecraftServer server,
            WorldServer worldServer,
            GameProfile profile,
            PlayerInteractManager playerInteractManager) {
        return NmsClassHelper.getInstance().createWithConstructor(
                EntityPlayer.class,
                new Class[]{
                        NmsPackage.NMS.getClass("MinecraftServer"),
                        NmsPackage.NMS.getClass("WorldServer"),
                        GameProfile.class,
                        NmsPackage.NMS.getClass("PlayerInteractManager")
                },
                server.getNmsHandle(),
                worldServer.getNmsHandle(),
                profile,
                playerInteractManager.getNmsHandle()
        );
    }
}
