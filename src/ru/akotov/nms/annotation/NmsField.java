package ru.akotov.nms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NmsField {
    Type value();

    boolean isStatic() default false;

    enum Type {
        GETTER, SETTER;
    }
}
