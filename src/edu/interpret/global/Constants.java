package edu.interpret.global;

import java.util.HashMap;
import java.util.Map;

import edu.interpret.exception.InterpretException;
import edu.parser.code.Const;

public class Constants {

    private static Map<String, Integer> constants;

    static {
        initialize();
    }

    public static void add(Const aConst) {
        if (!Constants.contains(aConst.getIdentifier())) {
            constants.put(aConst.getIdentifier(), aConst.getValue());
        } else {
            throw new InterpretException("Add constant: \"" + aConst.getIdentifier() + "\" already exists!");
        }
    }

    public static int getValue(String identifier) {
        if (contains(identifier)) {
            return constants.get(identifier);
        } else {
            throw new InterpretException("Get constant: \"" + identifier + "\" doesnt exists!");
        }
    }

    public static boolean contains(String identifier) {
        return constants.containsKey(identifier);
    }

    public static void initialize(){
        constants = new HashMap<>();
    }
}
