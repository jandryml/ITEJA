package edu.parser.code.statement;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.parser.code.variables.Value;

public class AssigmentStatement extends Statement {
    private String identifier;
    private Value value;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override public void process(Variables variables) {
        InterpretHelper.updateVariableValue(identifier, value, variables);
    }
}
