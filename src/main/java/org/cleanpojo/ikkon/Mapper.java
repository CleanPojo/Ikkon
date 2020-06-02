package org.cleanpojo.ikkon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Mapper {

    public <T> T map(Object source, Class<T> destination) {
        try {
            T instance = createInstance(source, destination);
            setProperties(source, instance);
            return instance;
        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException
            | NoSuchMethodException
            | SecurityException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static <T> T createInstance(Object source, Class<T> destination)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = getConstructor(destination);
        Object[] arguments = resolveArguments(source, constructor);
        return destination.cast(constructor.newInstance(arguments));
    }

    private static <T> Constructor<?> getConstructor(Class<T> destination)
            throws NoSuchMethodException {
        return destination.getConstructors()[0];
    }

    private static Object[] resolveArguments(Object source, Constructor<?> constructor)
            throws IllegalAccessException, InvocationTargetException {
        Parameter[] parameters = constructor.getParameters();
        var arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = resolveArgument(source, parameters[i]);
        }
        return arguments;
    }

    private static Object resolveArgument(Object source, Parameter parameter)
            throws IllegalAccessException, InvocationTargetException {
        String propertyName = getParameterName(parameter);
        Method getter = findGetter(source.getClass(), propertyName);
        return getter == null ? null : getter.invoke(source);
    }

    private static String getParameterName(Parameter parameter) {
        if (parameter.isNamePresent() == false) {
            String message = "The parameter does not have a name. Compile your code with '-parameters' option to include parameter names.";
            throw new RuntimeException(message);
        }
        return parameter.getName();
    }

    private static <T> void setProperties(Object source, T instance)
            throws IllegalAccessException, InvocationTargetException {
        for (Method method : instance.getClass().getMethods()) {
            if (isSetter(method)) {
                Method setter = method;
                setProperty(source, instance, setter);
            }
        }
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set");
    }

    private static <T> void setProperty(Object source, T instance, Method setter)
            throws IllegalAccessException, InvocationTargetException {
        String propertyName = setter.getName().substring(3);
        Method getter = findGetter(source.getClass(), propertyName);
        if (getter != null) {
            setter.invoke(instance, getter.invoke(source));
        }
    }

    private static Method findGetter(Class<?> type, String propertyName) {
        for (Method method : type.getMethods()) {
            if (method.getName().equalsIgnoreCase("get" + propertyName)) {
                return method;
            }
        }

        return null;
    }
}
