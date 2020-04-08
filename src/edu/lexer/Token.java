package edu.lexer;

import edu.lexer.enums.TokenType;

public class Token {
    private String value;
    private TokenType type;
    private int line;

    public Token(String value, int line) {
        this.value = value;
        this.line = line;
    }

    public Token(String value, TokenType type, int line) {
        this.value = value;
        this.type = type;
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
