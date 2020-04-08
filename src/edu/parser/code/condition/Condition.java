package edu.parser.code.condition;

import edu.lexer.enums.Grammar;

public abstract class Condition {
    Grammar type;

    public Condition(Grammar type) {
        this.type = type;
    }

    public Grammar getType() {
        return type;
    }

    public abstract boolean process();
}
