package ru.akotov.nms.cglib;

import java.util.Objects;

public final class ConstructorData {
    private final Class<?>[] params;
    private final Class<?> owner;

    private ConstructorData(Class<?>[] params, Class<?> owner) {
        this.params = params;
        this.owner = owner;
    }

    public static ConstructorData of(Class<?>[] params, Class<?> owner) {
        return new ConstructorData(params, owner);
    }

    public Class<?>[] getParams() {
        return this.params;
    }

    public Class<?> getOwner() {
        return this.owner;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConstructorData)) return false;
        final ConstructorData other = (ConstructorData) o;
        if (!java.util.Arrays.deepEquals(this.getParams(), other.getParams())) return false;
        final Object this$owner = this.getOwner();
        final Object other$owner = other.getOwner();
        if (!Objects.equals(this$owner, other$owner)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getParams());
        final Object $owner = this.getOwner();
        result = result * PRIME + ($owner == null ? 43 : $owner.hashCode());
        return result;
    }

    public String toString() {
        return "ConstructorData(params=" + java.util.Arrays.deepToString(this.getParams()) + ", owner=" + this.getOwner() + ")";
    }
}
