package edu.lexer;

import java.util.ArrayList;
import java.util.List;

import edu.lexer.enums.Grammar;

public class LexerHelper {
    private final List<Grammar> words = new ArrayList<>();
    private final List<Grammar> operators = new ArrayList<>();
    private final List<Grammar> separators = new ArrayList<>();
    private final List<Grammar> logicOperators = new ArrayList<>();

    public LexerHelper() {
        prepareKeyWords();
        prepareOperators();
        prepareSeparators();
        prepareLogicalOperators();
    }

    public boolean isThisAssigment(String value) {
        return Grammar.ASSIGMENT.getValue().equals(value);
    }

    public boolean isThisKeyWord(String value) {
        return words.stream().anyMatch((word) -> (word.getValue().equalsIgnoreCase(value)));
    }

    public boolean isThisLiteral(String literal) {
        try {
            Double.parseDouble(literal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isThisOperator(String operator) {
        return operators.stream().anyMatch((operatorFragment) -> (operator.equals(operatorFragment.getValue())));
    }

    public boolean isThisLogicalOperator(String operator) {
        return logicOperators.stream().anyMatch((operatorFragment) -> (operator.equals(operatorFragment.getValue())));
    }

    public boolean isThisSeparator(String value) {
        return separators.stream().anyMatch((char1) -> (char1.getValue().equals(value)));
    }

    public boolean isThisIdentifier(String variable) {
        return !words.stream().anyMatch((word) -> (word.getValue().equalsIgnoreCase(variable)));
    }

    private void prepareKeyWords() {
        words.add(Grammar.GLOBALS);
        words.add(Grammar.END);
        words.add(Grammar.FUNCTION);
        words.add(Grammar.MAIN);
        words.add(Grammar.NUMBER);
        words.add(Grammar.STRING);
        words.add(Grammar.IF);
        words.add(Grammar.WHILE);
        words.add(Grammar.FOR);
        words.add(Grammar.READ);
        words.add(Grammar.WRITE);
        words.add(Grammar.ASSIGMENT);
        words.add(Grammar.EXECUTE);
        words.add(Grammar.TERMINATE);
    }

    private void prepareOperators() {
        operators.add(Grammar.ROUND_BRACKET_LEFT);
        operators.add(Grammar.ROUND_BRACKET_RIGHT);
        operators.add(Grammar.PLUS);
        operators.add(Grammar.MINUS);
        operators.add(Grammar.MULTIPLY);
        operators.add(Grammar.MODULO);
        operators.add(Grammar.DIVISION);
        operators.add(Grammar.INCREMENT);
        operators.add(Grammar.DECREMENT);
    }

    private void prepareLogicalOperators() {
        logicOperators.add(Grammar.LESS);
        logicOperators.add(Grammar.LESS_OR_EQUAL);
        logicOperators.add(Grammar.GREATER);
        logicOperators.add(Grammar.GREATER_OR_EQUAL);
        logicOperators.add(Grammar.EQUAL);
        logicOperators.add(Grammar.NOT_EQUAL);
        logicOperators.add(Grammar.AND);
        logicOperators.add(Grammar.OR);
    }

    private void prepareSeparators() {
        separators.add(Grammar.SEMICOLON);
        separators.add(Grammar.COMMA);
        separators.add(Grammar.CURLY_BRACKET_LEFT);
        separators.add(Grammar.CURLY_BRACKET_RIGHT);
        separators.add(Grammar.SQUARE_BRACKET_LEFT);
        separators.add(Grammar.SQUARE_BRACKET_RIGHT);
        separators.add(Grammar.DOUBLE_QUOTE);
        separators.add(Grammar.SINGLE_QUOTE);
    }
}
