package edu.interpret;

import java.util.ArrayList;
import java.util.Scanner;

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
                        + "b = 3; \n"
                        + "if [ a == -1 && b != 2 && a == 0 + 1 - 2 ]{ \n"
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
                        + "EXECUTE writingTest[1, \"hello world\"]; \n"
                        + "EXECUTE writingTest[4, \"test\"]; \n"
                        + "EXECUTE writingTest[5, \"test2\"]; \n"
                        + "} \n"
                        + "FUNCTION writingTest [number aa, string bb] {\n"
                        + "WRITE aa; \n"
                        + "WRITE bb; \n"
                        + "} "
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }

    @Test
    public void interpretTest3() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "number y = 1 * ( 3 + 1 ); \n"
                        + "END \n"
                        + "MAIN { \n"
                        + "WRITE y;"
                        + "number xy = 1 * ( 3 + 1 ); \n"
                        + "WRITE xy;"
                        + "FOR[number i = 0; [ i < 5 ]; i++] {\n"
                        + "WRITE i; \n"
                        + "} \n"
                        + "} \n"
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }

    @Test
    public void interpretTest4() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "END \n"
                        + "MAIN { \n"
                        + "string a = \"a\";"
                        + "WRITE a;"
                        + "a = \"b\";"
                        + "WRITE a;"
                        + "number x = 0;"
                        + "WRITE x;"
                        + "x = x + 1;"
                        + "WRITE x;"
                        + "} \n"
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }


    @Test
    public void interpretTest5() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "END \n"
                        + "MAIN { \n"
                        + "number ap = 10, \n"
                        + "number a = 5 * ( 2 + 2 );"
                        + "WRITE ap;"
                        + "WRITE a;"
                        + "} \n"
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }

    @Test
    public void interpretTest6() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "END\n"
                        + "MAIN{\n"
                        + "WRITE \"Hello World!\";\n"
                        + "string x;\n"
                        + "number y;\n"
                        + "READ x;\n"
                        + "READ y;\n"
                        + "}\n"
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }
    @Test
    public void interpretTest7() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "GLOBALS\n"
                        + "    number targetValue = 13;\n"
                        + "END\n"
                        + "MAIN{\n"
                        + "    WRITE \"Welcome to number guessing game!\";\n"
                        + "    WRITE \"Try to guess!\";\n"
                        + "    \n"
                        + "    number guessed = 0;\n"
                        + "    \n"
                        + "    while [guessed != targetValue]{\n"
                        + "    READ guessed;\n"
                        + "    EXECUTE evaluate [guessed];\n"
                        + "    }\n"
                        + "    \n"
                        + "    WRITE \"Victory!\";\n"
                        + "}\n"
                        + "FUNCTION evaluate[number guessedValue]{\n"
                        + "    if [ guessedValue < targetValue] {\n"
                        + "     WRITE \"Try greater value!\";\n"
                        + "    }\n"
                        + "    \n"
                        + "    if [ guessedValue > targetValue] {\n"
                        + "         WRITE \"Try lower value!\";\n"
                        + "    }\n"
                        + "}\n"
                        + "TERMINATE");

        Parser parser = new Parser(new ParserHelper(tokens));

        Interpret interpret = new Interpret(parser.buildAst());

        interpret.process();
    }
}