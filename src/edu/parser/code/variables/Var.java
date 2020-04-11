package edu.parser.code.variables;

public class Var {
    private String identifier;
    private Value value;
    public Var() {
    }

    public Var(String identifier, Value value) {
        this.identifier = identifier;
        this.value = value;
    }

    public Var(String identifier) {
        this.identifier = identifier;
    }

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
}
