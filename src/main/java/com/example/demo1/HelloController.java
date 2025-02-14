package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    TextField text = new TextField();
    Label label = new Label("_Element");;

    @FXML
    protected void onHelloButtonClick() {
        label.setText(text.getText());
    }
}