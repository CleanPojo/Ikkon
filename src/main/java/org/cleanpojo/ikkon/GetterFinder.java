package org.cleanpojo.ikkon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

final class GetterFinder {

    public static Function<Object, TryResult> find(String propertyName, Class<?> type) {
        for (Method method : type.getMethods()) {
            if (isGetter(propertyName, method)) {
                return generalize(method);
            }

            if (method.getName().startsWith("get")
                    && propertyName.toLowerCase().startsWith(method.getName().substring(3).toLowerCase())) {
                String propertyName2 = propertyName.substring(method.getName().length() - 3);
                Class<?> type2 = method.getReturnType();
                Function<Object, TryResult> getter2 = find(propertyName2, type2);
                if (getter2 == null) {
                    return null;
                } else {
                    return instance -> {
                        Object instance2;
                        try {
                            instance2 = method.invoke(instance);
                        } catch (
                            IllegalAccessException
                            | IllegalArgumentException
                            | InvocationTargetException exception) {
                            return TryResult.failure(exception);
                        }
                        return getter2.apply(instance2);
                    };
                }
            }
        }

        return null;
    }

    private static Function<Object, TryResult> generalize(Method getter) {
        return instance -> invokeGetter(instance, getter);
    }

    private static TryResult invokeGetter(Object instance, Method getter) {
        try {
            return TryResult.success(getter.invoke(instance));
        } catch (
            IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException exception) {
            return TryResult.failure(exception);
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
