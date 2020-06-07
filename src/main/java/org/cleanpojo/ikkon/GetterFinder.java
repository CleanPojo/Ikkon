package org.cleanpojo.ikkon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class GetterFinder {

    public static Getter find(String propertyName, Class<?> type) {
        for (Method method : type.getMethods()) {
            if (isGetter(propertyName, method)) {
                return generalize(method);
            }

            if (isStemAccessor(propertyName, method)) {
                Method stemAccessor = method;
                return findLeafAccessor(propertyName, stemAccessor);
            }
        }

        return null;
    }

    private static boolean isStemAccessor(String propertyName, Method method) {
        return method.getName().startsWith("get")
            && propertyName.toLowerCase().startsWith(method.getName().substring(3).toLowerCase());
    }

    private static Getter findLeafAccessor(String propertyName, Method stemAccessor) {
        String propertyName2 = propertyName.substring(stemAccessor.getName().length() - 3);
        Class<?> type = stemAccessor.getReturnType();
        Getter getter = find(propertyName2, type);
        if (getter == null) {
            return null;
        } else {
            return instance -> {
                Object instance2;
                try {
                    instance2 = stemAccessor.invoke(instance);
                } catch (
                    IllegalAccessException
                    | IllegalArgumentException
                    | InvocationTargetException exception) {
                    return GetResult.failure(exception);
                }
                return getter.invoke(instance2);
            };
        }
    }

    private static Getter generalize(Method getter) {
        return instance -> invokeGetterMethod(instance, getter);
    }

    private static GetResult invokeGetterMethod(Object instance, Method getter) {
        try {
            return GetResult.success(getter.invoke(instance));
        } catch (
            IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException exception) {
            return GetResult.failure(exception);
        }
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
