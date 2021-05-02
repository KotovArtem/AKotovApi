package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsField;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "EnumHand")
public interface NmsEnumHand extends NmsHandler {

    static NmsEnumHand getEnum() {
        return NmsClassHelper.getInstance().getStatic(NmsEnumHand.class);
    }

    @NmsField(isStatic = true, value = NmsField.Type.GETTER)
    NmsEnumHand MAIN_HAND();

    @NmsField(isStatic = true, value = NmsField.Type.GETTER)
    NmsEnumHand OFF_HAND();

}