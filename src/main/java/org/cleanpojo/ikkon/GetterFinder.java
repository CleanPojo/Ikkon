package org.cleanpojo.ikkon;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

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

    public static Getter find2(Class<?> propertyType, String propertyName, Class<?> sourceType) {
        if (propertyType.equals(String.class)) {
            return null;
        }

        var methods = new ArrayList<Method>();
        for (Method method : sourceType.getMethods()) {
            if (method.getName().toLowerCase().startsWith(("get" + propertyName).toLowerCase())) {
                methods.add(method);
            }
        }

        if (methods.size() == 0) {
            return null;
        }

        return instance -> {
            try {
                Constructor<?> constructor = getConstructor(propertyType);
                String[] parameterNames = resolveParameterNames(constructor);
                var arguments = new Object[constructor.getParameters().length];
                for (int i = 0; i < constructor.getParameters().length; i++) {
                    for (Method method : methods) {
                        if (method.getName().toLowerCase().endsWith(parameterNames[i])) {
                            arguments[i] = method.invoke(instance);
                        }
                    }
                }
                return GetResult.success(constructor.newInstance(arguments));
            } catch (
                NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException exception) {
                return GetResult.failure(exception);
            }
        };
	}

    private static <T> Constructor<?> getConstructor(Class<T> destination)
            throws NoSuchMethodException {
        Constructor<?>[] constructors = destination.getConstructors();
        if (constructors.length > 1) {
            String message = "The type '" + destination.getName() + "' has multiple constructor.";
            throw new RuntimeException(message);
        }

        return constructors[0];
    }

    private static String[] resolveParameterNames(Constructor<?> constructor) {
        ConstructorProperties namesProvider = constructor.getAnnotation(ConstructorProperties.class);
        return namesProvider == null
            ? extractParameterNames(constructor)
            : getParameterNamesFromProvider(namesProvider);
    }

    private static String[] extractParameterNames(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        var names = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            names[i] = getParameterName(parameters[i]);
        }

        return names;
    }

    private static String getParameterName(Parameter parameter) {
        if (parameter.isNamePresent() == false) {
            String message = "The parameter does not have a name. Compile your code with '-parameters' option to include parameter names.";
            throw new RuntimeException(message);
        }

        return parameter.getName();
    }

    private static String[] getParameterNamesFromProvider(ConstructorProperties namesProvider) {
        return namesProvider.value();
    }
}
