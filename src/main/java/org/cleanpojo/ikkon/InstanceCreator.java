package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.ArgumentResolver.resolveArguments;
import static org.cleanpojo.ikkon.ConstructorResolver.resolveConstructor;

import java.lang.reflect.Constructor;

interface InstanceCreator {

    static <T> T createInstance(Object source, Class<T> destination)
            throws ReflectiveOperationException {

        Constructor<?> constructor = resolveConstructor(destination);
        Object[] arguments = resolveArguments(constructor, source);
        return destination.cast(constructor.newInstance(arguments));
    }
}
