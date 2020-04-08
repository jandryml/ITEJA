package edu.interpret.global;

import java.util.HashMap;
import java.util.Map;

import edu.interpret.exception.InterpretException;
import edu.parser.code.Procedure;
import edu.parser.code.statement.Statement;

public class Procedures {
    private static Map<String, Statement> procedures;

    static {
        initialize();
    }

    public static void add(Procedure procedure) {
        if (!Procedures.contains(procedure.getIdentifier())) {
            procedure.getBlock().getVarList().forEach(Variables::add);
            procedure.getBlock().getConstList().forEach(Constants::add);
            procedures.put(procedure.getIdentifier(), procedure.getBlock().getStatement());
        } else {
            throw new InterpretException("Create procedure: \"" + procedure.getIdentifier() + "\" already exists!");
        }
    }

    public static Statement getValue(String identifier) {
        if (contains(identifier)) {
            return procedures.get(identifier);
        } else {
            throw new InterpretException("Call procedure: \"" + identifier + "\" doesnt exists!");
        }
    }

    public static boolean contains(String identifier) {
        return procedures.containsKey(identifier);
    }

    public static void initialize() {
        procedures = new HashMap<>();
    }
}
