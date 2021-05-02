package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;

import java.util.Set;
import java.util.UUID;

@NmsClass(value = NmsPackage.NMS, handle = "NBTTagCompound")
public interface NmsNbtTagCompound extends NmsHandler {
    static NmsNbtTagCompound create() {
        return NmsClassHelper.getInstance().createWithConstructor(NmsNbtTagCompound.class);
    }

    @NmsMethod
    void set(String path, NmsNbtBase nbtBase);

    @NmsMethod
    void setString(String path, String value);

    @NmsMethod
    void setByte(String path, byte value);

    @NmsMethod
    void setShort(String path, short value);

    @NmsMethod
    void setInt(String path, int value);

    @NmsMethod
    void setLong(String path, long value);

    @NmsMethod
    void setFloat(String path, float value);

    @NmsMethod
    void setDouble(String path, double value);

    @NmsMethod
    void setBoolean(String path, boolean value);

    @NmsMethod
    void setByteArray(String path, byte[] value);

    @NmsMethod
    void setIntArray(String path, int[] value);

    @NmsMethod
    void a(String path, UUID uuid);

    @NmsMethod
    NmsNbtBase get(String path);

    @NmsMethod
    NmsNbtTagCompound getCompound(String path);

    @NmsMethod
    NmsNbtTagList getList(String path, int type);

    @NmsMethod
    byte getByte(String path);

    @NmsMethod
    short getShort(String path);

    @NmsMethod
    int getInt(String path);

    @NmsMethod
    long getLong(String path);

    @NmsMethod
    float getFloat(String path);

    @NmsMethod
    double getDouble(String path);

    @NmsMethod
    String getString(String path);

    @NmsMethod
    byte[] getByteArray(String path);

    @NmsMethod
    int[] getIntArray(String path);

    @NmsMethod
    boolean getBoolean(String path);

    @NmsMethod
    UUID a(String path);

    @NmsMethod
    void remove(String path);

    @NmsMethod
    boolean hasKey(String path);

    @NmsMethod
    boolean hasKeyOfType(String path, int type);

    default boolean hasUUIDKey(String path) {
        return hasKey(path + "Most") && hasKey(path + "Least");
    }

    @NmsMethod
    byte d(String path);

    @NmsMethod
    Set<String> c();

    @NmsMethod
    int d();

    @NmsMethod
    String toString();
}
