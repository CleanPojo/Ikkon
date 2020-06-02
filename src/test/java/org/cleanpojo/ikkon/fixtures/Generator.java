package org.cleanpojo.ikkon.fixtures;

import static java.util.UUID.randomUUID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
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
            arguments[i] = create(parameters[i].getType());
        }
        return arguments;
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
        Class<?> propertyType = setter.getParameters()[0].getType();
        setter.invoke(instance, create(propertyType));
    }
}
