package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.InstanceCreator.createInstance;
import static org.cleanpojo.ikkon.PropertySetter.setProperties;

public class Mapper {

    public <T> T map(Object source, Class<T> destination) {
        return source == null ? null : mapObject(source, destination);
    }

    private static <T> T mapObject(Object source, Class<T> destination) {
        try {
            T instance = createInstance(source, destination);
            setProperties(instance, source);
            return instance;
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
