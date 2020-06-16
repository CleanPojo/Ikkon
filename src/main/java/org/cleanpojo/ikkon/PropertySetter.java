package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ArgumentResolver.resolveArgument;

import java.lang.reflect.Method;

interface PropertySetter {

    static void setProperties(Object target, Object source)
            throws ReflectiveOperationException {

        String pathToSource = "";
        setProperties(target, pathToSource, source);
    }

    static void setProperties(Object target, String pathToSource, Object source)
            throws ReflectiveOperationException {

        for (Method method : target.getClass().getMethods()) {
            if (isSetter(method)) {
                Method setter = method;
                setProperty(target, setter, pathToSource, source);
            }
        }
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set")
            && method.getReturnType().equals(void.class)
            && method.getParameterCount() == 1;
    }

    static void setProperty(
            Object target,
            Method setter,
            String pathToSource,
            Object source)
            throws ReflectiveOperationException {

        var property = new PropertyHint(
            setter.getParameterTypes()[0],
            pathToSource + setter.getName().substring(3));

        Getter getter = GetterSelector.instance.select(source, property);

        if (getter != null) {
            setter.invoke(target, resolveArgument(property.getType(), getter));
        }
    }
}
