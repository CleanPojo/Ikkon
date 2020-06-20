package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.Getter.transpose;

import java.lang.reflect.Method;

final class SimpleGetterSelector implements GetterSelector {

    @Override
    public Getter select(Object source, PropertyHint property) {
        if (source == null) {
            return null;
        }

        for (Method method : source.getClass().getMethods()) {
            if (isGetter(property.getName(), method)) {
                return transpose(method, source);
            }
        }

        return null;
    }

    private static boolean isGetter(String propertyName, Method method) {
        return isGetAccessor(propertyName, method)
            || isPredicateAccessor(propertyName, method);
    }

    private static boolean isGetAccessor(String propertyName, Method method) {
        return method.getName().equalsIgnoreCase("get" + propertyName)
            && method.getReturnType().equals(void.class) == false
            && method.getParameterCount() == 0;
    }

    private static boolean isPredicateAccessor(String propertyName, Method method) {
        return method.getName().equalsIgnoreCase("is" + propertyName)
            && method.getReturnType().equals(boolean.class)
            && method.getParameterCount() == 0;
    }
}
