package edu.parser.code.variables;

public class StringVar {

    private String value;

    private StringVar() {
    }

    private StringVar(String identifier, String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
