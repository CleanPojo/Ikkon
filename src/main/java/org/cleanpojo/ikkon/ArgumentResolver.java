package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ParameterNameResolver.resolveParameterNames;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

interface ArgumentResolver {

    static Object[] resolveArguments(Constructor<?> constructor, Object source)
            throws IllegalAccessException, InvocationTargetException {

        String[] parameterNames = resolveParameterNames(constructor);
        var arguments = new Object[constructor.getParameterCount()];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = resolveArgument(constructor.getParameterTypes()[i], parameterNames[i], source);
        }

        return arguments;
    }

    private static Object resolveArgument(
            Class<?> parameterType,
            String parameterName,
            Object source)
            throws IllegalAccessException, InvocationTargetException {

        var property = new PropertyDescriptor(parameterType, parameterName);
        Getter getter = GetterSelector.instance.select(source, property);
        return getter == null
            ? DefaultValue.of(parameterType)
            : resolveArgument(parameterType, getter);
    }

    static Object resolveArgument(Class<?> parameterType, Getter getter) {
        GetResult result = getter.get();

        Exception exception = result.getException();
        if (exception != null) {
            throw new RuntimeException(exception);
        }

        return refineValue(parameterType, result.getValue());
    }

    private static Object refineValue(Class<?> parameterType, Object value) {
        return parameterType.equals(Iterable.class) ? toIterable((Iterable<?>)value)
             : parameterType.equals(Collection.class) ? toList((Iterable<?>)value)
             : parameterType.equals(List.class) ? toList((Iterable<?>)value)
             : isComplexType(parameterType) ? new Mapper().map(value, parameterType)
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
}
