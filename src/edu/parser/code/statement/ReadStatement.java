package edu.parser.code.statement;

import java.io.InputStream;
import java.util.Scanner;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.TokenType;
import edu.parser.code.variables.Value;

public class ReadStatement extends Statement {
    private static InputStream inputStream = System.in;

    private String identifier;

    public static void setInputStream(InputStream in) {
        inputStream = in;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public void process(Variables variables) {
        TokenType type = InterpretHelper.getValue(identifier, variables).getType();

        String dataTypeMessage = type.equals(TokenType.STRING) ? "string" : "number";

        System.out.print("Enter " + dataTypeMessage + " value for \"" + identifier + "\": ");

        Scanner scanner = new Scanner(inputStream);
        //TODO
        String input = "";
        try {
            Value value = new Value(type);
            input = scanner.nextLine();
            if (type.equals(TokenType.NUMBER)) {
                int result;
                if(input.isEmpty()){
                    result = 0;
                } else {
                    result = Integer.parseInt(input);
                }
                value.setExpressionValue(InterpretHelper.getExpressionOf(result));
            } else {
                value.setStringValue(input);
            }

            InterpretHelper.updateVariableValue(identifier, value, variables);
        } catch (NumberFormatException e) {
            throw new InterpretException("Invalid input: \"" + input + "\", expect type: " + type);
        }
    }
}
