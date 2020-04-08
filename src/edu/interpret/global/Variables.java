package edu.interpret.global;

import java.util.HashMap;
import java.util.Map;

import edu.interpret.exception.InterpretException;
import edu.parser.code.Var;

public class Variables {

    private static Map<String, Integer> variables;

    static {
        initialize();
    }

    public static void add(Var variable) {
        if (!Variables.contains(variable.getIdentifier())) {
            variables.put(variable.getIdentifier(), variable.getValue());
        } else {
            throw new InterpretException("Add variable: \"" + variable.getIdentifier() + "\" already exists!");
        }
    }

    public static void update(Var variable){
        if (Variables.contains(variable.getIdentifier())) {
            variables.put(variable.getIdentifier(), variable.getValue());
        } else {
            throw new InterpretException("Add variable: \"" + variable.getIdentifier() + "\" already exists!");
        }
    }

    public static int getValue(String identifier) {
        if (contains(identifier)) {
            return variables.get(identifier);
        } else {
            throw new InterpretException("Get variable: \"" + identifier + "\" doesnt exists!");
        }
    }

    public static boolean contains(String identifier) {
        return variables.containsKey(identifier);
    }

    public static void initialize(){
        variables = new HashMap<>();
    }
}
