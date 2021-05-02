package ru.akotov.utils.lazy;

import java.lang.ref.WeakReference;

public abstract class WeakLazy<T> implements Lazy<T> {
    private WeakReference<T> weakReference;

    @Override
    public T get() {
        T object = weakReference.get();
        if (object == null) {
            object = getIfNotPresent();
            weakReference = new WeakReference<>(object);
        }
        return object;
    }
}
