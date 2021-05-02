package ru.akotov.entity;

import ru.akotov.entity.util.EntityUtil;

public abstract class AbstractAKotovEntity implements AKotovEntity {
    private final int id;

    public AbstractAKotovEntity() {
        id = EntityUtil.getEntityId();
    }

    public int getId() {
        return id;
    }
}

