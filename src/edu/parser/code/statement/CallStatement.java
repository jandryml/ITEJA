package edu.parser.code.statement;

import edu.interpret.global.Procedures;
import edu.lexer.enums.Grammar;

public class CallStatement extends Statement{
    private String identifier;

    public CallStatement(Grammar type) {
        super(type);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public void process() {
        Procedures.getValue(identifier).process();
    }
}

