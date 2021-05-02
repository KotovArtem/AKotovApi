package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;

@NmsClass(value = NmsPackage.NMS, handle = "MinecraftServer")
public interface MinecraftServer extends NmsHandler {
}
