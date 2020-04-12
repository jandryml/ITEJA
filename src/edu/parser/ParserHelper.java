package edu.parser;

import java.util.ArrayList;

import edu.lexer.Token;
import edu.lexer.enums.Grammar;
import edu.lexer.enums.TokenType;

public class ParserHelper {
    private ArrayList<Token> tokenList;
    private int counter = 0;

    public ParserHelper(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        }

    public Token pop() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more tokens.");
        }
        return tokenList.get(counter++);
    }

    public Token peek() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more tokens.");
        }
        return tokenList.get(counter);
    }

    public TokenType peekType() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more tokens.");
        }
        return tokenList.get(counter).getType();
    }

    public String peekValue() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more tokens.");
        }
        return tokenList.get(counter).getValue();
    }

    public boolean isSame(TokenType tokenType, Grammar word){
        Token token = tokenList.get(counter);
        return token.getType().equals(tokenType) && token.getValue().toLowerCase().equals(word.getValue());
    }

    public boolean isType(TokenType type){
        return tokenList.get(counter).getType().equals(type);
    }

    public int getCount(){
        return tokenList.size();
    }

    public boolean hasNext() {
        return counter < tokenList.size();
    }

}
