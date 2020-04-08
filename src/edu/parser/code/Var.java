package edu.parser.code;

public class Var {
    private String identifier;
    private int value;

    public Var() {
    }

    public Var(String identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
