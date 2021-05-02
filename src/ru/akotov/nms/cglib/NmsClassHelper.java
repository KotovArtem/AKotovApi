package ru.akotov.nms.cglib;

import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.wrapper.NmsHandler;

import ru.akotov.utils.PrivateLookup;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.Map;

public final class NmsClassHelper {
    private final Map<Class<?>, Class<?>> NMS_CLASSES_BY_WRAPPERS = new HashMap<>();
    private final Map<MethodData, MethodHandle> CACHED_METHODS = new HashMap<>();
    private final Map<ConstructorData, Constructor> CACHED_CONSTRUCTORS = new HashMap<>();
    private final Map<Class<?>, Object> CACHED_STATIC_ACCESSORS = new HashMap<>();
    private static final NmsClassHelper INSTANCE = new NmsClassHelper();

    private NmsClassHelper() {
        if (INSTANCE != null)
            throw new RuntimeException("Unable create instance");
    }

    public static NmsClassHelper getInstance() {
        return INSTANCE;
    }

    public void cacheMethod(MethodData methodData, MethodHandle methodHandle) {
        CACHED_METHODS.put(methodData, methodHandle);
    }

    public MethodHandle getCachedMethod(MethodData methodData) {
        return CACHED_METHODS.get(methodData);
    }

    public Class<?> getAsNMSOrRegister( Class<?> clazz) {
        Class<?> nmsClass = NMS_CLASSES_BY_WRAPPERS.get(clazz);
        if (nmsClass == null) {
            if (clazz.isAnnotationPresent(NmsClass.class)) {
                NmsClass annotation = clazz.getAnnotation(NmsClass.class);
                nmsClass = annotation.value().getClass(annotation.handle());
                NMS_CLASSES_BY_WRAPPERS.put(clazz, nmsClass);
            } else {
                return clazz;
            }
        }
        return nmsClass;
    }

    @SuppressWarnings("unchecked")
    public <T> T createWithConstructor(Class<?> clazz, Object... args) {
        try {
            Class<?> nmsClass = getAsNMSOrRegister(clazz);

            Class<?>[] params = new Class<?>[args.length];
            for (int i = 0; i < params.length; i++) {
                params[i] = getAsNMSOrRegister(args[i].getClass());
            }

            ConstructorData constructorData = ConstructorData.of(params, clazz);
            Constructor constructor = CACHED_CONSTRUCTORS.get(constructorData);
            if (constructor == null) {
                constructor = nmsClass.getConstructor(params);
                CACHED_CONSTRUCTORS.put(constructorData, constructor);
            }
            args = unwrapArgs(constructor, args);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

        public <T> T createWithConstructor(Class<?> clazz, Class<?>[] params, Object... args) {
        try {
            Class<?> nmsClass = getAsNMSOrRegister(clazz);

            ConstructorData constructorData = ConstructorData.of(params, clazz);
            Constructor constructor = CACHED_CONSTRUCTORS.get(constructorData);
            if (constructor == null) {
                constructor = nmsClass.getConstructor(params);
                CACHED_CONSTRUCTORS.put(constructorData, constructor);
            }
            args = unwrapArgs(constructor, args);


        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
            return null;
        }

    @SuppressWarnings("unchecked")
    public <T> T asProxyInstance(Class<T> clazz, Object o) {
        if (o == null) return null;

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getStatic(Class<?> clazz) {
        Object accessor = CACHED_STATIC_ACCESSORS.get(clazz);
        if (accessor == null) {
            Class<?> nmsClass = NMS_CLASSES_BY_WRAPPERS.get(clazz);
            if (nmsClass == null) {
                NmsClass annotation = clazz.getAnnotation(NmsClass.class);
                nmsClass = annotation.value().getClass(annotation.handle());
                NMS_CLASSES_BY_WRAPPERS.put(clazz, nmsClass);
            }

        }

        return (T) accessor;
    }

    public Class<?>[] unwrap(Class<?>[] classes) {
        for (int i = 0; i < classes.length; i++) {
            Class<?> current = classes[i];
            classes[i] = getAsNMSOrRegister(current);
        }
        return classes;
    }

    public Object[] unwrapArgs(final Executable method, final Object[] args) {
        Object[] arguments = args;
        if (args != null) {
            arguments = new Object[args.length];
            Class<?>[] methodParams = method.getParameterTypes();
            for (int i = 0; i < methodParams.length; i++) {
                Object arg = args[i];
                Class<?> argClass = methodParams[i];
                if (argClass.isAnnotationPresent(NmsClass.class) && arg instanceof NmsHandler) {
                    arguments[i] = ((NmsHandler) arg).getNmsHandle();
                } else arguments[i] = arg;
            }
        }

        return arguments;
    }

    public Object createFromMethod(
            Object original,
            boolean isStatic,
            MethodHandle methodHandle,
            Object[] args) throws Throwable {
        if (!isStatic) {
            if (original == null)
                throw new RuntimeException();
            return methodHandle.bindTo(original).invokeWithArguments(args);
        } else {
            return methodHandle.invokeWithArguments(args);
        }
    }

    public Object createGetter(
            Object original,
            boolean isStatic,
            MethodHandle methodHandle,
            Object[] args) throws Throwable {
        if (!isStatic) {
            if (original == null)
                throw new RuntimeException();
            return methodHandle.bindTo(original).invokeWithArguments(args);
        } else {
            return methodHandle.invokeWithArguments(args);
        }
    }
}