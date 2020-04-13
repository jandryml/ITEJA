package edu;


import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 1000, 840));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

