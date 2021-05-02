package ru.akotov.utils;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;

public final class PrivateLookup {
    private static final Lookup LOOKUP;

    static {
        try {
            Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            LOOKUP = (Lookup) field.get(null);
            field.setAccessible(accessible);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Lookup get() {
        return LOOKUP;
    }
}

