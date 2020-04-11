package edu.parser.code.expression;

import java.util.List;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.TokenType;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;

public class Factor {

    private String identifier;
    private int literal;
    private Expression expression;
    private Types type;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        type = Types.IDENT;
        this.identifier = identifier;
    }

    public int getLiteral() {
        return literal;
    }

    public void setLiteral(int literal) {
        type = Types.LITERAL;
        this.literal = literal;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        type = Types.EXPRESSION;
        this.expression = expression;
    }

    public Types getType() {
        return type;
    }

    public enum Types {
        IDENT,
        LITERAL,
        EXPRESSION
    }

    public int calculateFactorValue(Variables variables){
        int result;

        if (identifier != null) {
            Value value = InterpretHelper.getValue(identifier, variables);
            if(value.getType().equals(TokenType.NUMBER)){
                result = value.getExpressionValue().calculateExpressionValue(variables);
            } else{
                throw new InterpretException("Variable \"" + identifier + "\" is not a NUMBER!");
            }
        } else if (expression != null) {
            result = expression.calculateExpressionValue(variables);
        } else {
            result = literal;
        }
        return result;
    }
}
