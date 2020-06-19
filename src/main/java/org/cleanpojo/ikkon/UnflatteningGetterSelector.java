package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ConstructorResolver.resolveConstructor;
import static org.cleanpojo.ikkon.ParameterNameResolver.resolveParameterNames;
import static org.cleanpojo.ikkon.PropertySetter.setProperties;
import static org.cleanpojo.ikkon.StringFunctions.startsWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final class UnflatteningGetterSelector implements GetterSelector {

    @Override
    public Getter select(Object source, PropertyHint property) {
        if (property.getType().equals(String.class)) {
            return null;
        }

        List<Method> getters = getGetters(property, source.getClass());

        if (getters.size() == 0) {
            return null;

        }

        return () -> unflattenTo(property, source, getters);
    }

    private static List<Method> getGetters(
            PropertyHint property, Class<?> source) {

        String prefix = "get" + property.getName();
        var getters = new ArrayList<Method>();
        for (Method method : source.getMethods()) {
            if (startsWith(method.getName(), prefix)) {
                getters.add(method);
            }
        }

        return getters;
    }

    private static Object unflattenTo(
            PropertyHint property,
            Object source,
            List<Method> getters)
            throws ReflectiveOperationException {

        Constructor<?> constructor = resolveConstructor(property.getType());
        Object[] arguments = resolveArguments(property, constructor, source, getters);
        Object instance = constructor.newInstance(arguments);
        setProperties(instance, property.getName(), source);
        return instance;
    }

    private static Object[] resolveArguments(
            PropertyHint property,
            Constructor<?> constructor,
            Object source,
            List<Method> getters)
            throws ReflectiveOperationException {

        String[] parameterNames = resolveParameterNames(constructor);
        var arguments = new Object[constructor.getParameterCount()];

        for (int i = 0; i < arguments.length; i++) {
            String parameterName = parameterNames[i];

            for (Method getter : getters) {
                if (getter.getName().equalsIgnoreCase("get" + property.getName() + parameterName)) {
                    arguments[i] = getter.invoke(source);
                } else if (startsWith(getter.getName(), "get" + property.getName() + parameterName)) {
                    arguments[i] = unflattenTo(new PropertyHint(constructor.getParameterTypes()[i], property.getName() + parameterName), source, getters);
                }
            }
        }

        return arguments;
    }
}
