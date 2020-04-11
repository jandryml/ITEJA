package edu.parser.code.condition;

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

}
