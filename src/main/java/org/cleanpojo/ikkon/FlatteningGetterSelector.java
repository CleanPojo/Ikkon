package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.StringFunctions.startsWith;

import java.lang.reflect.Method;

final class FlatteningGetterSelector implements GetterSelector {

    @Override
    public Getter select(Object source, PropertyHint property) {
        for (Method method : source.getClass().getMethods()) {
            if (isEdge(property.getName(), method)) {
                return () -> walkWithPath(source, property.getName(), method);
            }
        }

        return null;
    }

    private static boolean isEdge(String propertyName, Method method) {
        String methodName = method.getName();
        return startsWith(methodName, "get")
            && startsWith(propertyName, methodName.substring(3))
            && method.getReturnType().equals(void.class) == false
            && method.getParameterCount() == 0;
    }

    private static Object walkWithPath(
            Object vertex,
            String propertyName,
            Method edge)
            throws ReflectiveOperationException {

        Object nextVertex = edge.invoke(vertex);
        return getSubPathWalker(nextVertex, propertyName, edge).get();
    }

    private static Getter getSubPathWalker(Object nextVertex, String propertyName, Method edge) {
        var property = new PropertyHint(Object.class, trimEdgePath(propertyName, edge));
        return GetterSelector.instance.select(nextVertex, property);
    }

    private static String trimEdgePath(String propertyName, Method edge) {
        int pathLength = edge.getName().length() - 3;
        return propertyName.substring(pathLength);
    }
}
