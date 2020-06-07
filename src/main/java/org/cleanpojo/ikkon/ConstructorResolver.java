package org.cleanpojo.ikkon;

import java.lang.reflect.Constructor;

interface ConstructorResolver {

    static <T> Constructor<?> resolveConstructor(Class<T> destination)
            throws NoSuchMethodException {

        Constructor<?>[] constructors = destination.getConstructors();
        if (constructors.length > 1) {
            String message = "The type '" + destination.getName() + "' has multiple constructor.";
            throw new RuntimeException(message);
        }

        return constructors[0];
    }
}
