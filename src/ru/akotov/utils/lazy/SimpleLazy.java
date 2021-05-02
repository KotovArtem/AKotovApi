package ru.akotov.utils.lazy;

public abstract class SimpleLazy<T> implements Lazy<T> {
    private T object;

    @Override
    public T get() {
        if (object == null)
            object = getIfNotPresent();
        return object;
    }
}

