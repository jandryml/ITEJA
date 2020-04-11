package edu.parser.code.variables;

import edu.lexer.enums.TokenType;
import edu.parser.code.expression.Expression;

public class Value {
    private Expression expressionValue;
    private String stringValue;
    private TokenType type;

    public Value(TokenType type) {
        this.type = type;
    }

    public Expression getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(Expression expressionValue) {
        this.expressionValue = expressionValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
