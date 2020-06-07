package org.cleanpojo.ikkon;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

interface ParameterNameResolver {

    static String[] resolveParameterNames(Constructor<?> constructor) {
        ConstructorProperties namesProvider = constructor.getAnnotation(ConstructorProperties.class);
        return namesProvider == null
            ? reflectParameterNames(constructor)
            : getParameterNamesFromProvider(namesProvider);
    }

    private static String[] reflectParameterNames(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        var parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterNames[i] = reflectParameterName(parameters[i]);
        }

        return parameterNames;
    }

    private static String reflectParameterName(Parameter parameter) {
        if (parameter.isNamePresent() == false) {
            String message = "The parameter does not have a name. Compile your code with '-parameters' option to include parameter names.";
            throw new RuntimeException(message);
        }

        return parameter.getName();
    }

    private static String[] getParameterNamesFromProvider(ConstructorProperties namesProvider) {
        return namesProvider.value();
    }
}
