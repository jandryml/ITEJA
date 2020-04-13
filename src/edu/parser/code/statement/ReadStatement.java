package edu.parser.code.statement;

import edu.MainController;
import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.TokenType;
import edu.parser.code.variables.Value;

public class ReadStatement extends Statement {

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public void process(Variables variables) {
        TokenType type = InterpretHelper.getValue(identifier, variables).getType();

        String dataTypeMessage = type.equals(TokenType.STRING) ? "string" : "number";

        String message = "Enter " + dataTypeMessage + " value for \"" + identifier + "\": ";

        String input = "";
        try {
            Value value = new Value(type);
            input = MainController.readValueFromGUI(message);
            if (type.equals(TokenType.NUMBER)) {
                int result;
                if (input.isEmpty()) {
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
