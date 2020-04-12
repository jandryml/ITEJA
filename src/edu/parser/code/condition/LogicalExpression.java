package edu.parser.code.condition;

import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.Grammar;
import edu.parser.code.expression.Expression;

public class LogicalExpression {
    private Expression firstPart;
    private String operator;
    private Expression secondPart;

    public Expression getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(Expression firstPart) {
        this.firstPart = firstPart;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(Expression secondPart) {
        this.secondPart = secondPart;
    }

    public boolean process(Variables variables) {
        switch (Grammar.getType(operator)) {
        case LESS:
            return firstPart.calculateExpressionValue(variables) < secondPart.calculateExpressionValue(variables);
        case LESS_OR_EQUAL:
            return firstPart.calculateExpressionValue(variables) <= secondPart.calculateExpressionValue(variables);
        case EQUAL:
            return firstPart.calculateExpressionValue(variables) == secondPart.calculateExpressionValue(variables);
        case GREATER:
            return firstPart.calculateExpressionValue(variables) > secondPart.calculateExpressionValue(variables);
        case GREATER_OR_EQUAL:
            return firstPart.calculateExpressionValue(variables) >= secondPart.calculateExpressionValue(variables);
        case NOT_EQUAL:
            return firstPart.calculateExpressionValue(variables) != secondPart.calculateExpressionValue(variables);
        default:
            throw new InterpretException("Unexpected compare operator: " + operator);
        }
    }
}
