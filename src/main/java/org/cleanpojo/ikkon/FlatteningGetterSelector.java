package org.cleanpojo.ikkon;

import static org.cleanpojo.ikkon.StringFunctions.startsWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class FlatteningGetterSelector implements GetterSelector {

    @Override
    public Getter select(PropertyDescriptor property, Class<?> source) {
        for (Method method : source.getMethods()) {
            if (isEdge(property.getName(), method)) {
                return buildWalker(property.getName(), method);
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

    private static Getter buildWalker(String propertyName, Method edge) {
        return vertex -> walkWithPath(vertex, propertyName, edge);
    }

    private static GetResult walkWithPath(
            Object vertex,
            String propertyName,
            Method edge) {

        try {
            Object nextVertex = edge.invoke(vertex);
            return getSubPathWalker(propertyName, edge).invoke(nextVertex);
        } catch (
            IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException exception) {
            return GetResult.failure(exception);
        }
    }

    private static Getter getSubPathWalker(String propertyName, Method edge) {
        return GetterSelector.instance.select(
            new PropertyDescriptor(
                Object.class,
                trimEdgePath(propertyName, edge)),
            edge.getReturnType());
    }

    private static String trimEdgePath(String propertyName, Method edge) {
        int pathLength = edge.getName().length() - 3;
        return propertyName.substring(pathLength);
    }
}
