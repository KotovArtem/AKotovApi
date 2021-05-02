package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;

@NmsClass(value = NmsPackage.NMS, handle = "NBTBase")
public interface NmsNbtBase extends NmsHandler {
    @NmsMethod
    boolean isEmpty();

    @NmsMethod
    byte getTypeId();
}
