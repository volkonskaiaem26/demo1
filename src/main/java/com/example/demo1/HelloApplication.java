package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 960);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        TextField text = new TextField();
        text.setPromptText("Enter element");
        Label label = new Label("_Element");
        label.setLabelFor(text);
        label.setMnemonicParsing(true);
        Button button = new Button("Start");
        root.setCenter(button);
        button.setOnAction(event -> {
            System.out.println(text.getText());
        });
        Rectangle rect = new Rectangle(160, 120);
        BorderPane pane = new BorderPane();
        pane.setTop(rect);
        Scene scene1 = new Scene(pane);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}