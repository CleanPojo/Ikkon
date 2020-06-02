package org.cleanpojo.ikkon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Mapper {

    public <T> T map(Object source, Class<T> destination) {
        try {
            Constructor<T> constructor = destination.getConstructor();
            T instance = constructor.newInstance();
            for (Method method : destination.getMethods()) {
                if (method.getName().startsWith("set")) {
                    Method setter = method;
                    String propertyName = setter.getName().substring(3);
                    for (Method sourceMethod : source.getClass().getMethods()) {
                        if (sourceMethod.getName().equalsIgnoreCase("get" + propertyName)) {
                            Method getter = sourceMethod;
                            Object value = getter.invoke(source);
                            setter.invoke(instance, value);
                        }
                    }
                }
            }

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
}
