package edu.parser.code.statement;

import edu.lexer.enums.Grammar;

public abstract class Statement {
    private Grammar type;

    public Statement(Grammar type) {
        this.type = type;
    }

    public Grammar getType() {
        return type;
    }

    public abstract void process();
}
