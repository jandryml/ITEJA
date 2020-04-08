package edu.lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import edu.lexer.enums.Grammar;
import edu.lexer.enums.TokenType;

public class Lexer {

    public static ArrayList<Token> tokenize(String input) {
        return getSeparatedValues(input);
    }

    private static ArrayList<Token> getSeparatedValues(String input) {
        ArrayList<Token> separatedCode = new ArrayList<>();
        LexerHelper helper = new LexerHelper();
        int checkPoint = 0;
        int lineCount = 0;
        String rawWord;
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                lineCount++;
            }

            if (isSameAsOneOf(input.charAt(i), Grammar.DOUBLE_QUOTE.getValue(), Grammar.SINGLE_QUOTE.getValue())) {
                i += processString(input.substring(i + 1), separatedCode, lineCount);
                checkPoint = i;
                continue;
            }

            if (helper.isThisSeparator(Character.toString(input.charAt(i)))) {
                separatedCode.add(new Token(Character.toString(input.charAt(i)), TokenType.SEPARATOR, lineCount));
                checkPoint = i + 1;
                continue;
            }

            rawWord = input.substring(checkPoint, i + 1);

            if ((i + 1 < input.length() && helper.isThisLogicalOperator(rawWord + input.charAt(i + 1)))) {
                separatedCode.add(new Token(rawWord + input.charAt(i + 1), TokenType.OPERATOR, lineCount));
                checkPoint = i + 2;
                continue;
            }

            if (helper.isThisLogicalOperator(rawWord) || helper.isThisOperator(rawWord)) {
                separatedCode.add(new Token(rawWord, TokenType.OPERATOR, lineCount));
                checkPoint = i + 1;
                continue;
            }

            if (isThisWhiteSpace(rawWord)) {
                checkPoint = i + 1;
                continue;
            }

            if (helper.isThisKeyWord(rawWord)) {
                separatedCode.add(new Token(rawWord, TokenType.KEYWORD, lineCount));
                checkPoint = i + 1;
                continue;
            }

            if (isThisWhiteSpace(charToString(input, i + 1)) ||
                    helper.isThisSeparator(charToString(input, i + 1))) {
                if (Pattern.matches("[a-zA-Z]+", rawWord)) {
                    separatedCode.add(new Token(rawWord, TokenType.IDENTIFIER, lineCount));
                } else if (Pattern.matches("[0-9]+", rawWord)) {
                    separatedCode.add(new Token(rawWord, TokenType.NUMBER, lineCount));
                } else {
                    String postfix = rawWord.substring(rawWord.length() - 2);
                    if (postfix.equals(Grammar.INCREMENT.getValue()) || postfix.equals(Grammar.DECREMENT.getValue())) {
                        separatedCode.add(new Token(rawWord.substring(0, rawWord.length() - 2), TokenType.IDENTIFIER, lineCount));
                        separatedCode.add(new Token(postfix, TokenType.OPERATOR, lineCount));
                    } else {
                        separatedCode.add(new Token(rawWord, TokenType.INVALID, lineCount));
                    }
                }
                checkPoint = i + 1;
            }
        }
        return separatedCode;
    }

    private static boolean isThisWhiteSpace(String value) {
        return value.chars().allMatch(Character::isWhitespace);
    }

    private static int processString(String input, ArrayList<Token> separatedCode, int lineCount) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; !isSameAsOneOf(input.charAt(i), Grammar.DOUBLE_QUOTE.getValue(), Grammar.SINGLE_QUOTE.getValue()); i++) {
            sb.append(input.charAt(i));
        }
        String result = sb.toString();
        separatedCode.add(new Token(result, TokenType.STRING, lineCount));

        return result.length() + 1;
    }

    private static boolean isSameAsOneOf(char value, String... strings) {
        for (String string : strings) {
            if (string != null && string.length() == 1) {
                return string.charAt(0) == value;
            }
        }
        return false;
    }

    private static String charToString(String rawCode, int position) {
        if (position < rawCode.length()) {
            return Character.toString(rawCode.charAt(position));
        }
        return Character.toString(rawCode.charAt(rawCode.length() - 1));
    }
}
