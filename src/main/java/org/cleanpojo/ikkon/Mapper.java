package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.InstanceCreator.createInstance;
import static org.cleanpojo.ikkon.PropertySetter.setProperties;

public class Mapper {

    public <T> T map(Object source, Class<T> destination) {
        try {
            T instance = createInstance(source, destination);
            setProperties(source, instance);
            return instance;
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
