package edu.parser.code.statement;

import edu.interpret.global.InterpretHelper;
import edu.lexer.enums.Grammar;
import edu.parser.code.expression.Expression;

public class SetStatement extends Statement{
    private String identifier;
    private Expression expression;

    public SetStatement(Grammar type) {
        super(type);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override public void process() {
        InterpretHelper.updateVariableValue(identifier, expression.process());
    }
}
