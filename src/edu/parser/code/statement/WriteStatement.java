package edu.parser.code.statement;

import java.io.PrintStream;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.lexer.enums.TokenType;
import edu.parser.code.variables.Value;
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
        Value value;
        if(variables.contains(var.getIdentifier())){
            value = variables.getValue(var.getIdentifier());
        } else {
            value = var.getValue();
        }
        String pValue = value.getType().equals(TokenType.STRING) ?
                value.getStringValue() :
                String.valueOf(value.getExpressionValue().calculateExpressionValue(variables));
        printStream.println(pValue);
    }

}
