package edu.parser.code.variables;

import edu.parser.code.expression.Expression;

public class NumberVar   {

    private Expression expression;

    private NumberVar() {
    }

    private NumberVar(String identifier, Expression expression) {
        this.expression = expression;
    }

    private Expression getExpression() {
        return expression;
    }

    private void setExpression(Expression expression) {
        this.expression = expression;
    }
}
