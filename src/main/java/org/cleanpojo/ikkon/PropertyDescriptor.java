package org.cleanpojo.ikkon;

import java.lang.reflect.Method;

final class PropertyDescriptor {

    private final Class<?> type;
    private final String name;

    public PropertyDescriptor(final Class<?> type, final String name) {
        this.type = type;
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static PropertyDescriptor fromSetter(final Method setter) {
        return new PropertyDescriptor(
            setter.getParameterTypes()[0],
            setter.getName().substring(3));
    }
}
