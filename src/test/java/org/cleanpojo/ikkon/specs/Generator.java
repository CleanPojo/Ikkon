package org.cleanpojo.ikkon.specs;

import static java.util.UUID.randomUUID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class Generator {

    private static final Random random = new Random();

    public static <T> T create(Class<T> type) {
        if (type.equals(UUID.class)) {
            return type.cast(randomUUID());
        } else if (type.equals(String.class)) {
            return type.cast(randomUUID().toString());
        } else if (type.equals(int.class)) {
            return type.cast(random.nextInt());
        } else if (type.equals(long.class)) {
            return createLong();
        } else if (type.equals(boolean.class)) {
            return createBoolean();
        } else if (type.isPrimitive() == false) {
            try {
                return createObject(type);
            } catch (
                InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Could not create object of type '" + type.getName() + "'.");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T createBoolean() {
        return (T)Boolean.class.cast(random.nextInt() % 2 == 0);
    }

    @SuppressWarnings("unchecked")
    private static <T> T createLong() {
        return (T)Long.class.cast(random.nextLong());
    }

    public static UUID createUUID() {
        return create(UUID.class);
    }

    public static String createString() {
        return create(String.class);
    }

    private static <T> T createObject(Class<T> type)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        T instance = createInstance(type);
        setProperties(instance, type);
        return instance;
    }

    private static <T> T createInstance(Class<T> type)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> constructor = getGreediestConstructor(type);
        Object[] arguments = generateArguments(constructor);
        return type.cast(constructor.newInstance(arguments));
    }

    private static <T> Constructor<?> getGreediestConstructor(Class<T> type) {
        Constructor<?>[] constructors = type.getConstructors();
        Comparator<Constructor<?>> comparator = Comparator.comparing(Constructor::getParameterCount);
        Arrays.sort(constructors, comparator.reversed());
        return constructors[0];
    }

    private static Object[] generateArguments(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        var arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = generateArgument(parameters[i]);
        }
        return arguments;
    }

    private static Object generateArgument(Parameter parameter) {
        Class<?> type = parameter.getType();
        return type.equals(Iterable.class)
            ? generateList((ParameterizedType)parameter.getParameterizedType())
            : create(type);
    }

    private static Object generateList(ParameterizedType parameterizedType) {
        Type typeArgument = parameterizedType.getActualTypeArguments()[0];
        Class<?> t = Class.class.cast(typeArgument);
        return List.of(create(t), create(t), create(t));
    }

    private static <T> void setProperties(T instance, Class<T> type)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Method method : type.getMethods()) {
            if (isSetter(method)) {
                setProperty(instance, method);
            }
        }
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set")
            && method.getReturnType().equals(void.class)
            && method.getParameterCount() == 1;
    }

    private static <T> void setProperty(T instance, Method setter)
            throws IllegalAccessException, InvocationTargetException {
        Parameter parameter = setter.getParameters()[0];
        setter.invoke(instance, generateArgument(parameter));
    }
}
