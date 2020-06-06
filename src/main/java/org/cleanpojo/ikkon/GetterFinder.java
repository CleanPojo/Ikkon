package org.cleanpojo.ikkon;

import java.lang.reflect.Method;

final class GetterFinder {

    public static Method find(String propertyName, Class<?> type) {
        for (Method method : type.getMethods()) {
            if (isGetter(propertyName, method)) {
                return method;
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
            && method.getReturnType().equals(void.class) == false;
    }

    private static boolean isPredicateAccessor(String propertyName, Method method) {
        return method.getName().equalsIgnoreCase("is" + propertyName)
            && method.getReturnType().equals(boolean.class);
    }
}
