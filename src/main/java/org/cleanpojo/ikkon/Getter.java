package org.cleanpojo.ikkon;

import java.lang.reflect.Method;

interface Getter {

    Object get() throws ReflectiveOperationException;

    static Getter transpose(Method g, Object x) {
        return () -> g.invoke(x);
    }
}
