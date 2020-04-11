package edu.parser.exception;

public class ParserException extends RuntimeException {

    private final String error;

    public ParserException(String error) {
        this.error = error;
    }

    public String getMessage() {
        return error;
    }
}