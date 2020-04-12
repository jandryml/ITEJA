package edu.parser.code.statement;

import java.io.PrintStream;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.lexer.enums.TokenType;
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

    @Override public void process(Variables variables) {
        Var var = InterpretHelper.transferVariable(this.var, variables);

        String pValue = var.getValue().getType().equals(TokenType.STRING) ?
                var.getValue().getStringValue() :
                String.valueOf(var.getValue().getExpressionValue().calculateExpressionValue(variables));
        printStream.println(pValue);
        printStream.flush();
    }

}
