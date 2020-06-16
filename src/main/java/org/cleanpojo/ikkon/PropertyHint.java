package org.cleanpojo.ikkon;

import java.lang.reflect.Method;

final class PropertyHint {

    private final Class<?> type;
    private final String name;

    public PropertyHint(final Class<?> type, final String name) {
        this.type = type;
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static PropertyHint fromSetter(final Method setter) {
        return new PropertyHint(
            setter.getParameterTypes()[0],
            setter.getName().substring(3));
    }
}
