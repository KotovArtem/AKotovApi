package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;
import org.bukkit.Server;
import org.bukkit.command.SimpleCommandMap;

@NmsClass(value = NmsPackage.CB, handle = "CraftServer")
public interface CraftServer extends NmsHandler {
    static CraftServer getInstance(Server handle) {
        return NmsClassHelper.getInstance().asProxyInstance(CraftServer.class, handle);
    }

    @NmsMethod
    SimpleCommandMap getCommandMap();

    @NmsMethod
    MinecraftServer getServer();
}
