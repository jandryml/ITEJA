package edu.interpret.global;

import java.io.InputStream;
import java.io.PrintStream;

import edu.interpret.exception.InterpretException;
import edu.parser.code.Var;
import edu.parser.code.statement.ReadStatement;
import edu.parser.code.statement.WriteStatement;

public class InterpretHelper {

    public static int getValue(String identifier) {
        try {
            return Constants.getValue(identifier);
        } catch (InterpretException e) {
            return Variables.getValue(identifier);
        }
    }

    public static void updateVariableValue(String identifier, int value) {
        if (Variables.contains(identifier)) {
            Var var = new Var(identifier, value);
            Variables.update(var);
        } else {
            if (Constants.contains(identifier)) {
                throw new InterpretException("Constant value cannot be changed!");
            } else {
                throw new InterpretException("Invalid identifier, variable does not exists!");
            }
        }
    }

    public static int evaluateNumberSign(int value, String sign) {
        if (sign != null) {
            value = sign.equals("-") ? value * -1 : value;
        }
        return value;
    }

    public static void setPrintStream(PrintStream printStream) {
        WriteStatement.setPrintStream(printStream);
    }

    public static void setInputStream(InputStream in) {
        ReadStatement.setInputStream(in);
    }
}
