package edu.parser;

import java.util.ArrayList;

import org.junit.Test;

import edu.lexer.Lexer;
import edu.lexer.Token;

public class ParserTest {

    @Test
    public void parserTest1() {
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

        parser.buildAst();
    }

    @Test
    public void parserTest2() {
        ArrayList<Token> tokens = Lexer.tokenize(
                "CONST\n"
                        + "  m =  7,\n"
                        + "  n = 85;\n"
                        + "\n"
                        + "VAR\n"
                        + "  x, y, z, q, r;\n"
                        + "\n"
                        + "PROCEDURE multiply;\n"
                        + "VAR a, b;\n"
                        + "\n"
                        + "BEGIN\n"
                        + "  a := x;\n"
                        + "  b := y;\n"
                        + "  z := 0;\n"
                        + "  WHILE b > 0 DO BEGIN\n"
                        + "    IF ODD b THEN z := z + a;\n"
                        + "    a := 2 * a;\n"
                        + "    b := b / 2\n"
                        + "  END\n"
                        + "END;\n"
                        + "\n"
                        + "PROCEDURE divide;\n"
                        + "VAR w;\n"
                        + "BEGIN\n"
                        + "  r := x;\n"
                        + "  q := 0;\n"
                        + "  w := y;\n"
                        + "  WHILE w <= r DO w := 2 * w;\n"
                        + "  WHILE w > y DO BEGIN\n"
                        + "    q := 2 * q;\n"
                        + "    w := w / 2;\n"
                        + "    IF w <= r THEN BEGIN\n"
                        + "      r := r - w;\n"
                        + "      q := q + 1\n"
                        + "    END\n"
                        + "  END\n"
                        + "END;\n"
                        + "\n"
                        + "PROCEDURE gcd;\n"
                        + "VAR f, g;\n"
                        + "BEGIN\n"
                        + "  f := x;\n"
                        + "  g := y;\n"
                        + "  WHILE f # g DO BEGIN\n"
                        + "    IF f < g THEN g := g - f;\n"
                        + "    IF g < f THEN f := f - g\n"
                        + "  END;\n"
                        + "  z := f\n"
                        + "END;\n"
                        + "\n"
                        + "BEGIN\n"
                        + "  x := m;\n"
                        + "  y := n;\n"
                        + "  CALL multiply;\n"
                        + "  x := 25;\n"
                        + "  y :=  3;\n"
                        + "  CALL divide;\n"
                        + "  x := 84;\n"
                        + "  y := 36;\n"
                        + "  CALL gcd\n"
                        + "END.");

        Parser parser = new Parser(new ParserHelper(tokens));

        parser.buildAst();
    }
}