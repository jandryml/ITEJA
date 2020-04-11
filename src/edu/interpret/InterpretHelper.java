package edu.interpret;

import java.io.InputStream;
import java.io.PrintStream;

import edu.parser.code.statement.ReadStatement;
import edu.parser.code.statement.WriteStatement;
import edu.parser.code.variables.Value;

public class InterpretHelper {


    public static int getValue(String identifier) {
        //TODO
//        try {
//            return Constants.getValue(identifier);
//        } catch (InterpretException e) {
//            return Variables.getValue(identifier);
//        }
        return 0;
    }


    public static void updateVariableValue(String identifier, Value value) {
//        if (Variables.contains(identifier)) {
//            Var var = new Var(identifier, value);
//            Variables.update(var);
//        } else {
//            if (Constants.contains(identifier)) {
//                throw new InterpretException("Constant value cannot be changed!");
//            } else {
//                throw new InterpretException("Invalid identifier, variable does not exists!");
//            }
//        }
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
