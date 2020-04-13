package edu;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import edu.interpret.Functions;
import edu.interpret.Globals;
import edu.interpret.Interpret;
import edu.lexer.Lexer;
import edu.lexer.Token;
import edu.parser.Parser;
import edu.parser.ParserHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

public class MainController implements Initializable {

    @FXML public TextArea codeTextArea;
    @FXML public TextArea consoleTextArea;
    @FXML public ComboBox<String> templateChoiceBox;

    public static String readValueFromGUI(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Variable input");
        dialog.setHeaderText(message);

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    @FXML
    public void runProgram() {
        Globals.initialize();
        Functions.initialize();
        try {

            ArrayList<Token> tokens = Lexer.tokenize(codeTextArea.getText());

            Parser parser = new Parser(new ParserHelper(tokens));

            Interpret interpret = new Interpret(parser.buildAst());

            interpret.process();
            consoleTextArea.appendText("$ PROGRAM SUCCESSFULLY TERMINATED $\n");

        } catch (RuntimeException e) {
            consoleTextArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    @FXML
    public void loadTemplate() {
        String selected = templateChoiceBox.getSelectionModel().getSelectedItem();
        String fileName;
        if (selected.contains("1)")) {
            fileName = "test1.txt";
        } else if (selected.contains("2)")) {
            fileName = "test2.txt";
        } else if (selected.contains("3)")) {
            fileName = "test3.txt";
        } else {
            consoleTextArea.appendText("Error: " + "file not found!" + "\n");
            return;
        }

        File file = new File(getClass().getClassLoader().getResource("code/" + fileName).getFile());

        if (file != null) {
            StringBuilder contentBuilder = new StringBuilder();

            try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
                codeTextArea.clear();
                codeTextArea.setText(contentBuilder.toString());
            } catch (IOException e) {
                consoleTextArea.appendText("Error: " + "error during reading file" + "\n");

            }
        } else {
            consoleTextArea.appendText("Error: " + "file not found!" + "\n");
        }

    }

    @FXML
    public void clearOutput() {
        consoleTextArea.clear();
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        ConsoleOut console = new ConsoleOut(consoleTextArea);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        initializeTemplatesChoiceBox();

    }

    private void initializeTemplatesChoiceBox() {
        List<String> choices = new ArrayList<>();
        choices.add("1) Hello World");
        choices.add("2) Iterate");
        choices.add("3) Guess number");
        templateChoiceBox.getItems().addAll(choices);
        templateChoiceBox.getSelectionModel().select(0);
    }
}
