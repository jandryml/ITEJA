package edu.parser.code.statement;

import java.io.PrintStream;

import edu.interpret.global.InterpretHelper;
import edu.lexer.enums.Grammar;

public class WriteStatement extends Statement {
    private static PrintStream printStream = System.out;

    private String identifier;

    public WriteStatement(Grammar type) {
        super(type);
    }

    public static void setPrintStream(PrintStream pStream) {
        printStream = pStream;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public void process() {
        printStream.println(InterpretHelper.getValue(identifier));
    }
}
