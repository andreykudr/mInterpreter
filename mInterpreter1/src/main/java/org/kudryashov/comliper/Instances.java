package org.kudryashov.comliper;

import org.kudryashov.comliper.elements.elementTypes.repositories.Identifiers;
import org.kudryashov.comliper.elements.elementTypes.repositories.Separators;
import org.kudryashov.comliper.elements.elementTypes.repositories.Words;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;

import java.util.HashMap;
import java.util.Map;

public class Instances {

    private final static Map<String, Object> instances = new HashMap<>();

    public static final String VARIABLE_TYPES = "variableTypes";
    public static final String IDENTIFIERS = "identifiers";
    public static final String OPERATORS = "operators";
    public static final String WORDS = "words";
    public static final String SEPARATORS = "separators";

    static {
        init();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String instanceName) {
        if (instances.containsKey(instanceName)) {
            return (T) instances.get(instanceName);
        }
        throw new IllegalArgumentException("have not instance with name " + instanceName);
    }

    public static void reset() {
        init();
    }

    private static void init() {
        instances.put(VARIABLE_TYPES, new VariableTypes());
        instances.put(IDENTIFIERS, new Identifiers());
        instances.put(WORDS, new Words());
        instances.put(SEPARATORS, new Separators());
    }
}
