package org.cleanpojo.ikkon;

import java.lang.reflect.Method;

final class SimpleGetterSelector implements GetterSelector {

    @Override
    public Getter select(PropertyDescriptor property, Class<?> source) {
        for (Method method : source.getMethods()) {
            if (isGetter(property.getName(), method)) {
                return Getter.fromMethod(method);
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
