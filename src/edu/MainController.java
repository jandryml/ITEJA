package edu;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.interpret.Functions;
import edu.interpret.Globals;
import edu.interpret.Interpret;
import edu.lexer.Lexer;
import edu.lexer.Token;
import edu.parser.Parser;
import edu.parser.ParserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MainController implements Initializable {

    @FXML public TextArea codeTextArea;
    @FXML public TextArea consoleTextArea;

    @FXML
    public void runProgram() {
        consoleTextArea.clear();
        Globals.initialize();
        Functions.initialize();
//       try {

            ArrayList<Token> tokens = Lexer.tokenize(codeTextArea.getText());

            Parser parser = new Parser(new ParserHelper(tokens));

            Interpret interpret = new Interpret(parser.buildAst());

            interpret.process();
//        } catch (RuntimeException e){
//            consoleTextArea.appendText(e.getMessage());
//        }
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        ConsoleOut console = new ConsoleOut(consoleTextArea);
        PrintStream ps = new PrintStream(console, true);
       // System.setOut(ps);
       // System.setErr(ps);
    }
}
