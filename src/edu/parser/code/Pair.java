package edu.parser.code;

public class Pair<F, S> {
    private F value;
    private S operator;

    public Pair(F value, S operator) {
        this.value = value;
        this.operator = operator;
    }

    public F getValue() {
        return value;
    }

    public void setValue(F value) {
        this.value = value;
    }

    public S getOperator() {
        return operator;
    }

    public void setOperator(S operator) {
        this.operator = operator;
    }
}
