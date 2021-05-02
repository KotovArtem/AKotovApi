package ru.akotov.nms.cglib;

public final class MethodData {
    private final Class<?> owner;
    private final String methodName;
    private final Class<?> returnType;
    private final Class<?>[] params;

    private MethodData(Class<?> owner, String methodName, Class<?> returnType, Class<?>[] params) {
        this.owner = owner;
        this.methodName = methodName;
        this.returnType = returnType;
        this.params = params;
    }

    public static MethodData of(Class<?> owner, String methodName, Class<?> returnType, Class<?>[] params) {
        return new MethodData(owner, methodName, returnType, params);
    }

    public Class<?> getOwner() {
        return this.owner;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Class<?> getReturnType() {
        return this.returnType;
    }

    public Class<?>[] getParams() {
        return this.params;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MethodData)) return false;
        final MethodData other = (MethodData) o;
        final Object this$owner = this.getOwner();
        final Object other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) return false;
        final Object this$methodName = this.getMethodName();
        final Object other$methodName = other.getMethodName();
        if (this$methodName == null ? other$methodName != null : !this$methodName.equals(other$methodName))
            return false;
        final Object this$returnType = this.getReturnType();
        final Object other$returnType = other.getReturnType();
        if (this$returnType == null ? other$returnType != null : !this$returnType.equals(other$returnType))
            return false;
        if (!java.util.Arrays.deepEquals(this.getParams(), other.getParams())) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $owner = this.getOwner();
        result = result * PRIME + ($owner == null ? 43 : $owner.hashCode());
        final Object $methodName = this.getMethodName();
        result = result * PRIME + ($methodName == null ? 43 : $methodName.hashCode());
        final Object $returnType = this.getReturnType();
        result = result * PRIME + ($returnType == null ? 43 : $returnType.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getParams());
        return result;
    }

    public String toString() {
        return "MethodData(owner=" + this.getOwner() + ", methodName=" + this.getMethodName() + ", returnType=" + this.getReturnType() + ", params=" + java.util.Arrays.deepToString(this.getParams()) + ")";
    }
}
