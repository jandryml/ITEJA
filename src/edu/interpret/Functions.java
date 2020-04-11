package edu.interpret;

import java.util.HashMap;
import java.util.Map;

import edu.interpret.exception.InterpretException;
import edu.parser.code.Function;

public class Functions {
    private static Map<String, Function> functions;

    static {
        initialize();
    }

    public static void add(Function function) {
        if (!Functions.contains(function.getIdentifier())) {
            functions.put(function.getIdentifier(), function);
        } else {
            throw new InterpretException("Create function: \"" + function.getIdentifier() + "\" already exists!");
        }
    }

    public static Function getValue(String identifier) {
        if (contains(identifier)) {
            return functions.get(identifier);
        } else {
            throw new InterpretException("Call function: \"" + identifier + "\" doesnt exists!");
        }
    }

    public static boolean contains(String identifier) {
        return functions.containsKey(identifier);
    }

    public static void initialize() {
        functions = new HashMap<>();
    }
}
