package edu.interpret.exception;

public class InterpretException extends RuntimeException {

    private final String error;

    public InterpretException(String error) {
        this.error = error;
    }

    public String getMessage() {
        return error;
    }
}