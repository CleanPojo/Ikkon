package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ConstructorResolver.resolveConstructor;
import static org.cleanpojo.ikkon.ParameterNameResolver.resolveParameterNames;
import static org.cleanpojo.ikkon.StringFunctions.startsWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// TODO: Refactor internal design
final class UnflatteningGetterSelector implements GetterSelector {

    @Override
    public Getter select(PropertyDescriptor property, Class<?> source) {
        if (property.getType().equals(String.class)) {
            return null;
        }

        final List<Method> unflatteningGetters = getUnflatteningGetters(property, source);
        return unflatteningGetters.size() == 0 ? null : instance -> unflatten(property, unflatteningGetters, instance);
    }

    private static List<Method> getUnflatteningGetters(PropertyDescriptor property, Class<?> source) {
        String prefix = "get" + property.getName();
        var unflatteningGetters = new ArrayList<Method>();
        for (Method method : source.getMethods()) {
            if (startsWith(method.getName(), prefix)) {
                unflatteningGetters.add(method);
            }
        }
        return unflatteningGetters;
    }

    private static GetResult unflatten(PropertyDescriptor property, List<Method> unflatteningGetters, Object instance) {
        try {
            final Constructor<?> constructor = resolveConstructor(property.getType());
            final Object[] arguments = resolveArguments(constructor, unflatteningGetters, instance);
            return GetResult.success(constructor.newInstance(arguments));
        } catch (
            NoSuchMethodException
            | InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException exception) {
            return GetResult.failure(exception);
        }
    }

    private static Object[] resolveArguments(
            final Constructor<?> constructor,
            final List<Method> unflatteningGetters,
            final Object instance)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final String[] parameterNames = resolveParameterNames(constructor);
        final var arguments = new Object[constructor.getParameters().length];

        for (int i = 0; i < constructor.getParameters().length; i++) {
            final String parameterName = parameterNames[i];

            for (final Method method : unflatteningGetters) {
                if (method.getName().toLowerCase().endsWith(parameterName)) {
                    arguments[i] = method.invoke(instance);
                }
            }
        }

        return arguments;
    }
}
