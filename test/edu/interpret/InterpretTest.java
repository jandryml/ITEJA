package edu.interpret;

import java.util.ArrayList;

import org.junit.Test;

import edu.lexer.Lexer;
import edu.lexer.Token;
import edu.parser.Parser;
import edu.parser.ParserHelper;

public class InterpretTest {
    @Test
    public void interpretTest1() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "number a =  -7 + 3 * 2,\n"
                        + "number b,\n"
                        + "string  c,\n"
                        + "string  d = \"Hello world\";\n"
                        + "END \n"
                        + "MAIN { \n"
                        + "EXECUTE writingTest[a, d]; \n"
                        + "EXECUTE writingTest[4, d]; \n"
                        + "if [ a == 7 && b != 2 && a == 0 + 1 + 3 ]{ \n"
                        + "EXECUTE writingTest[7, \"ahoj\"]; \n"
                        + "} \n"
                        + "FOR[number i = 0; [ i < 5 ]; i++] {\n"
                        + "WRITE i; \n"
                        + "WHILE[ i < 2 ] { \n"
                        + "WRITE i + 10; \n"
                        + "i = i + 1;\n"
                        + "} \n"
                        + "} \n"
                        + " \n"
                        + "number xy = 1 * ( 3 + 1 ); \n"
                        + "} \n"
                        + "FUNCTION writingTest [number aa, string bb] {\n"
                        + "WRITE \"test\"; \n"
                        + "WRITE 6; \n"
                        + "WRITE aa; \n"
                        + "WRITE bb; \n"
                        + "} "
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }

    @Test
    public void interpretTest2() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "string  d = \"Hello world\";\n"
                        + "END \n"
                        + "MAIN { \n"
                        + "EXECUTE writingTest[4, d]; \n"
                        + "} \n"
                        + "FUNCTION writingTest [number aa, string bb] {\n"
                        + "WRITE \"test\"; \n"
                        + "WRITE 6; \n"
                        + "WRITE aa; \n"
                        + "WRITE bb; \n"
                        + "} "
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }
}