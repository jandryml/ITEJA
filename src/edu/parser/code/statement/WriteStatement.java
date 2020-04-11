package edu.parser.code.statement;

import java.io.PrintStream;

import edu.interpret.InterpretHelper;
import edu.parser.code.variables.Var;

public class WriteStatement extends Statement {
    private static PrintStream printStream = System.out;
    private Var var;

    public static void setPrintStream(PrintStream pStream) {
        printStream = pStream;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }
    @Override public void process() {
        printStream.println(InterpretHelper.getValue(var.getIdentifier()));
    }

}
