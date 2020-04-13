package edu.interpret;

import java.io.InputStream;
import java.io.PrintStream;

import edu.interpret.exception.InterpretException;
import edu.lexer.enums.TokenType;
import edu.parser.code.expression.Expression;
import edu.parser.code.expression.Factor;
import edu.parser.code.expression.Term;
import edu.parser.code.statement.ReadStatement;
import edu.parser.code.statement.WriteStatement;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;

public class InterpretHelper {

    public static Value getValue(String identifier, Variables variables) {
        try {
            return variables.getValue(identifier);
        } catch (InterpretException e) {
            return Globals.getValue(identifier);
        }
    }

    public static void addVariable(String identifier, Value value, Variables variables) {
        if (!Globals.contains(identifier)) {
            Var var = new Var(identifier, value);
            variables.add(var);
        } else {
            throw new InterpretException("Variable \"" + identifier + "\" already exists on global scope!");
        }
    }

    public static void updateVariableValue(String identifier, Value value, Variables variables) {
        Value actualValue =getValue(identifier, variables);
        if(!actualValue.getType().equals(value.getType())){
            throw new InterpretException("Invalid types assigment");
        }

        if (variables.contains(identifier)) {
            if (value.getType().equals(TokenType.NUMBER)) {
                Expression simplifiedExpression = getExpressionOf(value.getExpressionValue().calculateExpressionValue(variables));
                value = new Value(TokenType.NUMBER);
                value.setExpressionValue(simplifiedExpression);
            }
            Var var = new Var(identifier, value);
            variables.update(var);
        } else {
            if (Globals.contains(identifier)) {
                Var var = new Var(identifier, value);
                Globals.update(var);
            } else {
                throw new InterpretException(
                        "Invalid identifier \"" + identifier + "\" , variable does not exists on local, neither on global scope!");
            }
        }
    }

    public static Expression getExpressionOf(int value) {
        Expression expression = new Expression();
        Term term = new Term();
        Factor factor = new Factor();
        factor.setLiteral(value);
        term.addFactor(factor, null);
        expression.addTerm(term, null);
        return expression;
    }

    public static int evaluateNumberSign(int value, String sign) {
        if (sign != null) {
            value = sign.equals("-") ? value * -1 : value;
        }
        return value;
    }

    public static Var transferVariable(Var var, Variables variables) throws InterpretException {
        if (var.getValue().getType().equals(TokenType.STRING)) {
            return var;
        } else if (var.getValue().getType().equals(TokenType.NUMBER)) {
            try {
                Expression expression = var.getValue().getExpressionValue();
                if (expression.getExpressionList().size() == 1
                        && expression.getExpressionList().get(0).getValue().getFactorList().size() == 1) {
                    Factor factor = expression.getExpressionList().get(0).getValue().getFactorList().get(0).getValue();
                    String identifier = factor.getIdentifier();
                    if (identifier != null) {
                        Value value = InterpretHelper.getValue(identifier, variables);
                        if (value.getType().equals(TokenType.STRING)) {
                            return new Var("param", value);
                        } else if (value.getType().equals(TokenType.NUMBER)){
                            Value updatedValue = new Value(TokenType.NUMBER);
                            updatedValue.setExpressionValue(getExpressionOf(value.getExpressionValue().calculateExpressionValue(variables)));
                            return new Var(var.getIdentifier(), updatedValue);
                        }
                    }
                }
                return var;
            } catch (InterpretException e) {
                throw new InterpretException("Invalid param");
            }
        } else {
            throw new InterpretException("Unknown data type!");
        }
    }

    public static void setPrintStream(PrintStream printStream) {
        WriteStatement.setPrintStream(printStream);
    }

}
