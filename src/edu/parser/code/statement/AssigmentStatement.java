package edu.parser.code.statement;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;

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
        Value value = InterpretHelper.transferVariable(new Var(identifier, this.value), variables).getValue();
        InterpretHelper.updateVariableValue(identifier, value, variables);
    }
}
