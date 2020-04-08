package edu.parser.code.condition;

import edu.lexer.enums.Grammar;
import edu.parser.code.expression.Expression;

public class OddCondition extends Condition {
    private Expression expression;

    public OddCondition(Grammar type) {
        super(type);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override public boolean process() {
        //return (expression.process() % 2) == 1;
        return (expression.process() & 1) != 0;
    }
}
