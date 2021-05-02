package ru.akotov.nms.annotation;

import ru.akotov.nms.NmsPackage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NmsClass {
    NmsPackage value();

    String handle();
}
