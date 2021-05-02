package ru.akotov.utils.lazy;

import java.util.function.Supplier;

public interface Lazy<T> extends Supplier<T> {
    T getIfNotPresent();
}

