package edu.lexer.enums;

public enum Grammar {
    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    INCREMENT("++"),
    DECREMENT("--"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    MODULO("%"),
    ASSIGMENT("="),
    AND("&&"),
    OR("||"),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    EQUAL("=="),
    NOT_EQUAL("!="),
    CURLY_BRACKET_LEFT("{"),
    CURLY_BRACKET_RIGHT("}"),
    ROUND_BRACKET_LEFT("("),
    ROUND_BRACKET_RIGHT(")"),
    COMMA(","),
    SEMICOLON(";"),
    WRITE("write"),
    READ("read"),
    FOR("for"),
    WHILE("while"),
    IF("if"),
    STRING("string"),
    NUMBER("number"),
    MAIN("main"),
    FUNCTION("function"),
    END("end"),
    GLOBALS("globals");

    private final String value;

    Grammar(String value) {
        this.value = value;
    }

    public static Grammar getType(String value) {
        for (Grammar grammar : Grammar.values()) {
            if (value.equals(grammar.getValue())) {
                return grammar;
            }
        }
        throw new RuntimeException("Value is not present.");
    }

    public String getValue() {
        return value;
    }
}
