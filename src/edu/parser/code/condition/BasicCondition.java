package edu.parser.code.condition;

import edu.interpret.exception.InterpretException;
import edu.lexer.Token;
import edu.lexer.enums.Grammar;
import edu.parser.code.expression.Expression;

public class BasicCondition extends Condition {

    private Expression firsExpression;
    private Expression secondExpression;
    private Token operator;

    public BasicCondition(Grammar type) {
        super(type);
    }

    public Expression getFirsExpression() {
        return firsExpression;
    }

    public void setFirsExpression(Expression firsExpression) {
        this.firsExpression = firsExpression;
    }

    public Expression getSecondExpression() {
        return secondExpression;
    }

    public void setSecondExpression(Expression secondExpression) {
        this.secondExpression = secondExpression;
    }

    public Token getOperator() {
        return operator;
    }

    public void setOperator(Token operator) {
        this.operator = operator;
    }

    @Override public boolean process() {
        switch (Grammar.getType(operator.getValue())) {
        case LESS:
            return firsExpression.process() < secondExpression.process();
        case LESS_OR_EQUAL:
            return firsExpression.process() <= secondExpression.process();
//        case EQUALS:
//            return firsExpression.process() == secondExpression.process();
        case GREATER:
            return firsExpression.process() > secondExpression.process();
        case GREATER_OR_EQUAL:
            return firsExpression.process() >= secondExpression.process();
//        case HASH:
//            return firsExpression.process() != secondExpression.process();
        default:
            throw new InterpretException("Unexpected compare operator: " + operator.getValue());
        }
    }
}
