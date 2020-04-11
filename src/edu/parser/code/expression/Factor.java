package edu.parser.code.expression;


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
}
