package org.cleanpojo.ikkon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface Getter {

    GetResult get();

    static Getter transpose(Method g, Object x) {
        return () -> {
            try {
                return GetResult.success(g.invoke(x));
            } catch (
                IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException exception) {
                return GetResult.failure(exception);
            }
        };
    }
}
