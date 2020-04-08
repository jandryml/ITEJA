package edu.lexer;

import java.util.ArrayList;

import edu.lexer.enums.TokenType;

public class Lexer {

    public static ArrayList<Token> tokenize(String input) {
        ArrayList<Token> tokenList = getSeparatedValues(input);

        return defineType(tokenList);
    }

    public static ArrayList<Token> getSeparatedValues(String input) {
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

            rawWord = input.substring(checkPoint, i + 1);

            if (isThisWhiteSpace(rawWord)) {
                checkPoint = i + 1;
                continue;
            }
            if (i + 2 < input.length() && helper.isThisAssigment(input.substring(i, i + 2))) {
                separatedCode.add(new Token(input.substring(i, i + 2), lineCount));
                checkPoint = i + 2;
                continue;
            }
            if (isThisWhiteSpace(charToString(input, i + 1)) ||
                    helper.isThisSeparator(charToString(input, i + 1))
                    || (i + 3 < input.length() && helper.isThisAssigment(input.substring(i + 1, i + 3)))) {
                separatedCode.add(new Token(rawWord, lineCount));
                checkPoint = i + 1;
                continue;
            }

        }
        return separatedCode;
    }

    private static boolean isThisWhiteSpace(String value) {
        return value.chars().allMatch(Character::isWhitespace);
    }

    public static ArrayList<Token> defineType(ArrayList<Token> tokenList) {
        LexerHelper helper = new LexerHelper();
        for (int i = 0; i < tokenList.size(); i++) {
            Token token = tokenList.get(i);
            String tokenValue = tokenList.get(i).getValue();
            if (helper.isThisAssigment(tokenValue)) {
                token.setType(TokenType.ASSIGMENT);
            } else if (helper.isThisKeyWord(tokenValue)) {
                token.setType(TokenType.KEYWORD);
            } else if (helper.isThisLiteral(tokenValue)) {
                token.setType(TokenType.LITERAL);
            } else if (helper.isThisOperator(tokenValue)) {
                token.setType(TokenType.OPERATOR);
            } else if (helper.isThisSeparator(tokenValue)) {
                token.setType(TokenType.SEPARATOR);
            } else if (helper.isThisIdentifier(tokenValue)) {
                token.setType(TokenType.IDENTIFIER);
            } else {
                token.setType(TokenType.INVALID);
            }
            tokenList.set(i, token);
        }
        return tokenList;
    }

    private static String charToString(String rawCode, int position) {
        if (position < rawCode.length()) {
            return Character.toString(rawCode.charAt(position));
        }
        return Character.toString(rawCode.charAt(rawCode.length() - 1));
    }
}
