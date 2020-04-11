package edu.parser.code.statement;

import java.io.InputStream;
import java.util.Scanner;

import edu.interpret.InterpretHelper;

public class ReadStatement extends Statement{
    private static InputStream inputStream;

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

    @Override public void process() {
        Scanner scanner = new Scanner(inputStream);
        try {
            System.out.print("Enter new numeric value of \"" + identifier + "\": ");
            InterpretHelper.updateVariableValue(identifier, Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
         //   throw new InterpretException(e.getMessage());
        }
    }
}
