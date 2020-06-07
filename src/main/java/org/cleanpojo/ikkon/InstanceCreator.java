package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ArgumentResolver.resolveArguments;
import static org.cleanpojo.ikkon.ConstructorResolver.resolveConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

interface InstanceCreator {

    static <T> T createInstance(Object source, Class<T> destination)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Constructor<?> constructor = resolveConstructor(destination);
        Object[] arguments = resolveArguments(constructor, source);
        return destination.cast(constructor.newInstance(arguments));
    }
}
