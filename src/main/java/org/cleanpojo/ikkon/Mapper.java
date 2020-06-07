package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.InstanceCreator.createInstance;
import static org.cleanpojo.ikkon.PropertySetter.setProperties;

import java.lang.reflect.InvocationTargetException;

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
}
