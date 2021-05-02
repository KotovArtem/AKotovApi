package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;

@NmsClass(value = NmsPackage.NMS, handle = "NBTTagList")
public interface NmsNbtTagList extends NmsNbtBase, NmsHandler {
    static NmsNbtTagList create() {
        return NmsClassHelper.getInstance().createWithConstructor(NmsNbtTagList.class);
    }

    @NmsMethod
    NmsNbtBase i(int index);

    @NmsMethod
    NmsNbtTagCompound get(int index);

    @NmsMethod
    String getString(int index);

    @NmsMethod(nmsName = "c")
    int getInt(int index);

    @NmsMethod
    double f(int index);

    @NmsMethod
    float g(int index);

    @NmsMethod
    int[] d(int index);

    @NmsMethod
    NmsNbtBase remove(int index);

    @NmsMethod
    int size();

    @NmsMethod
    void add(NmsNbtBase nbtBase);

    @NmsMethod
    int g();

}
