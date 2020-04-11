package edu.interpret;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.interpret.exception.InterpretException;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;

public class Variables {
    private Map<String, Value> variables;

    public Variables() {
        variables = new HashMap<>();
    }

    public void setVariables(List<Var> variables) {
        variables.forEach(this::add);
    }

    public void add(Var variable) {
        if (!contains(variable.getIdentifier())) {
            variables.put(variable.getIdentifier(), variable.getValue());
        } else {
            throw new InterpretException("Add variable: \"" + variable.getIdentifier() + "\" already exists in local scope!");
        }
    }

    public void update(Var variable) {
            variables.put(variable.getIdentifier(), variable.getValue());
    }

    public Value getValue(String identifier) {
        if (contains(identifier)) {
            return variables.get(identifier);
        } else {
            throw new InterpretException("Get variable: \"" + identifier + "\" doesnt exists!");
        }
    }

    public boolean contains(String identifier) {
        return variables.containsKey(identifier);
    }
}
