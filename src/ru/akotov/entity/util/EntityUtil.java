package ru.akotov.entity.util;

import ru.akotov.nms.NmsPackage;
import ru.akotov.utils.PrivateLookup;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;

public final class EntityUtil {
    private static final MethodHandle ID_SETTER, ID_GETTER;

    static {
        try {
            Class entity = Class.forName(NmsPackage.NMS + ".Entity");
            Field field = entity.getDeclaredField("entityCount");
            ID_SETTER = PrivateLookup.get().unreflectSetter(field);
            ID_GETTER = PrivateLookup.get().unreflectGetter(field);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getEntityId() {
        int id;

        try {
            id = (int) ID_GETTER.invokeExact();
            ID_SETTER.invokeExact(id + 1);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }

        return id;
    }

    public static int floor(double number) {
        int var2 = (int) number;
        return number < (double) var2 ? var2 - 1 : var2;
    }
}
