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

    private <T> T createInstance(Object source, Class<T> destination)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = getConstructor(destination);
        Parameter[] parameters = constructor.getParameters();
        var arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isNamePresent() == false) {
                String message = "The parameter does not have a name. Compile your code with '-parameters' option to include parameter names.";
                throw new RuntimeException(message);
            }
            String propertyName = parameter.getName();
            Method getter = findGetter(source.getClass(), propertyName);
            if (getter != null) {
                arguments[i] = getter.invoke(source);
            }
        }
        return destination.cast(constructor.newInstance(arguments));
    }

    private <T> Constructor<?> getConstructor(Class<T> destination)
            throws NoSuchMethodException {
        return destination.getConstructors()[0];
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
