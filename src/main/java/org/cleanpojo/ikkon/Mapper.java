package org.cleanpojo.ikkon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Mapper {

    public <T> T map(Object source, Class<T> destination) {
        try {
            T instance = createInstance(destination);
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

    private <T> T createInstance(Class<T> destination)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<T> constructor = getConstructor(destination);
        return constructor.newInstance();
    }

    private <T> Constructor<T> getConstructor(Class<T> destination)
            throws NoSuchMethodException {
        return destination.getConstructor();
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

    private boolean isSetter(Method method) {
        return method.getName().startsWith("set");
    }

    private <T> void setProperty(Object source, T instance, Method setter)
            throws IllegalAccessException, InvocationTargetException {
        String propertyName = setter.getName().substring(3);
        Method getter = findGetter(source.getClass(), propertyName);
        if (getter != null) {
            setter.invoke(instance, getter.invoke(source));
        }
    }

    private Method findGetter(Class<?> type, String propertyName) {
        for (Method method : type.getMethods()) {
            if (method.getName().equalsIgnoreCase("get" + propertyName)) {
                return method;
            }
        }

        return null;
    }
}
