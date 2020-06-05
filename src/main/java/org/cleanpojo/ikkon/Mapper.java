package org.cleanpojo.ikkon;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    private <T> T createInstance(Object source, Class<T> destination)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = getConstructor(destination);
        Object[] arguments = resolveArguments(source, constructor);
        return destination.cast(constructor.newInstance(arguments));
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

    private Object[] resolveArguments(Object source, Constructor<?> constructor)
            throws IllegalAccessException, InvocationTargetException {

        String[] names = getParameterNames(constructor);
        var arguments = new Object[constructor.getParameters().length];
        for (int i = 0; i < constructor.getParameters().length; i++) {
            arguments[i] = resolveArgument(source, constructor.getParameters()[i], names[i]);
        }

        return arguments;
    }

    private static String[] getParameterNames(Constructor<?> constructor) {
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

    private static String[] getParameterNamesFromProvider(ConstructorProperties namesProvider) {
        return namesProvider.value();
    }

    private Object resolveArgument(Object source, Parameter parameter,
            String propertyName)
            throws IllegalAccessException, InvocationTargetException {
        Method getter = findGetter(source.getClass(), propertyName);
        return getter == null ? null : resolveArgument(source, parameter, getter);
    }

    private Object resolveArgument(Object source, Parameter parameter, Method getter)
            throws IllegalAccessException, InvocationTargetException {
        Class<?> parameterType = parameter.getType();
        Object value = getter.invoke(source);
        return parameterType.equals(Iterable.class)
            ? toIterable((Iterable<?>)value)
            : parameterType.equals(Collection.class)
            ? toList((Iterable<?>)value)
            : parameterType.equals(List.class)
            ? toList((Iterable<?>)value)
            : isComplexType(parameterType)
            ? map(value, value.getClass())
            : value;
    }

    private static <T> Iterable<T> toIterable(Iterable<T> iterable) {
        var list = new ArrayList<T>();
        iterable.forEach(list::add);
        return Collections.unmodifiableList(list);
    }

    private static <T> List<T> toList(Iterable<T> iterable) {
        var list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list;
    }

    private static boolean isComplexType(Class<?> type) {
        return type.isPrimitive() == false
            && type.equals(String.class) == false
            && type.equals(UUID.class) == false;
    }

    private static String getParameterName(Parameter parameter) {
        if (parameter.isNamePresent() == false) {
            String message = "The parameter does not have a name. Compile your code with '-parameters' option to include parameter names.";
            throw new RuntimeException(message);
        }

        return parameter.getName();
    }

    private <T> void setProperties(Object source, T instance)
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

    private <T> void setProperty(Object source, T instance, Method setter)
            throws IllegalAccessException, InvocationTargetException {
        String propertyName = setter.getName().substring(3);
        Method getter = findGetter(source.getClass(), propertyName);
        if (getter != null) {
            Parameter parameter = setter.getParameters()[0];
            setter.invoke(instance, resolveArgument(source, parameter, getter));
        }
    }

    private static Method findGetter(Class<?> type, String propertyName) {
        for (Method method : type.getMethods()) {
            if (method.getName().equalsIgnoreCase("get" + propertyName)) {
                return method;
            } else if (method.getName().equalsIgnoreCase("is" + propertyName)) {
                return method;
            }
        }

        return null;
    }
}
