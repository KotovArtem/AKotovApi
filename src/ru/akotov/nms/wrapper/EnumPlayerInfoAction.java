package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsField;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "PacketPlayOutPlayerInfo$EnumPlayerInfoAction")
public interface EnumPlayerInfoAction extends NmsHandler {
    static EnumPlayerInfoAction getEnum() {
        return NmsClassHelper.getInstance().getStatic(EnumPlayerInfoAction.class);
    }

    @NmsField(isStatic = true, value = NmsField.Type.GETTER)
    EnumPlayerInfoAction ADD_PLAYER();
}
