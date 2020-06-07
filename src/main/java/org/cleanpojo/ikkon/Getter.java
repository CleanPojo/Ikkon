package org.cleanpojo.ikkon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface Getter {

    GetResult invoke(Object instance);

    static Getter fromMethod(Method getterMethod) {
        return instance -> {
            try {
                return GetResult.success(getterMethod.invoke(instance));
            } catch (
                IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException exception) {
                return GetResult.failure(exception);
            }
        };
    }
}
