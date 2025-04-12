package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.Duration;


import java.io.IOException;


public class HelloApplication extends Application {

    int COLOR_SNOW = 1;
    int COLOR_KHAKI = 2;
    int COLOR_DODGERDBLUE = 3;
    int COLOR_DARKSLATEDGREY = 4;
    int COLOR_DARKGREEN = 5;
    int COLOR_MAROON = 6;
    int COLOR_FIREBRICK = 7;
    int COLOR_BLANCHEDALMOND = 8;

    int GAS_COLOR_TRANSPARENT = 11;
    int GAS_COLOR_DARKRED = 12;
    int GAS_COLOR_DARKKHAKI = 13;
    int GAS_COLOR_MEDIUMBLUE = 14;


    @Override
    public void start(Stage stage) throws IOException { //создание окна программы
        Label lbl = new Label();//вывод
        lbl.setLayoutY(300.0);
        lbl.setLayoutX(180.0);

        TextField textField = new TextField();//ввод
        textField.setPrefColumnCount(25);
        textField.setLayoutY(20.0);
        textField.setLayoutX(100.0);

        Button btn = new Button("Start");//начало работы
        btn.setLayoutY(60.0);
        btn.setLayoutX(207.0);

        Rectangle rectangle = new Rectangle(60.0d, 120.0d);//центральная колба
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        Group group = new Group(rectangle);
        group.setLayoutY(150.0);
        group.setLayoutX(200.0);

        Rectangle rectangle1 = new Rectangle(58.0d, 59.0d);//верхняя половина(раствор)
        rectangle1.setFill(Color.TRANSPARENT);
        rectangle1.setStroke(Color.TRANSPARENT);
        Group group1 = new Group(rectangle1);
        group1.setLayoutY(151.0);
        group1.setLayoutX(201.0);

        Rectangle rectangle2 = new Rectangle(58.0d, 59.0d);//нижняя половина(осадок)
        rectangle2.setFill(Color.TRANSPARENT);
        rectangle2.setStroke(Color.TRANSPARENT);
        Group group2 = new Group(rectangle2);
        group2.setLayoutY(210.0);
        group2.setLayoutX(201.0);

        Circle circle1 = new Circle( 5.0d);
        circle1.setFill(Color.TRANSPARENT);
        Group groupCircle1 = new Group(circle1);
        groupCircle1.setLayoutX(getRandom(205, 220));
        groupCircle1.setLayoutY(getRandom(215, 245));

        Circle circle2 = new Circle(5.0d);
        circle2.setFill(Color.TRANSPARENT);
        Group groupCircle2 = new Group(circle2);
        groupCircle2.setLayoutX(getRandom(220, 240));
        groupCircle2.setLayoutY(getRandom(225, 255));

        Circle circle3 = new Circle(5.0d);
        circle3.setFill(Color.TRANSPARENT);
        Group groupCircle3 = new Group(circle3);
        groupCircle3.setLayoutX(getRandom(240, 255));
        groupCircle3.setLayoutY(getRandom(215, 245));



        btn.setOnAction(event -> {
            String text = textField.getText();
            String st = "";
            boolean isCorrect = false;
            if(text.contains("+")){
                isCorrect = true;
                FormulaParser formulas1 = new FormulaParser(text);
                String a = formulas1.getA();
                String b = formulas1.getB();
                Main main = new Main(a,b);
                st += main.reaction();
                int SedimentColor = getSedimentColor(st);
                int GasColor = getGasColor(st);
                switch(SedimentColor){
                    case 1: rectangle2.setFill(Color.SNOW);
                    case 2: rectangle2.setFill(Color.KHAKI);
                    case 3: rectangle2.setFill(Color.DODGERBLUE);
                    case 4: rectangle2.setFill(Color.DARKSLATEGREY);
                    case 5: rectangle2.setFill(Color.DARKGREEN);
                    case 6: rectangle2.setFill(Color.MAROON);
                    case 7: rectangle2.setFill(Color.FIREBRICK);
                    case 8: rectangle2.setFill(Color.BLANCHEDALMOND);
                }
                if(GasColor!=0){
                    setGasColor(circle1, GasColor);
                    setGasColor(circle2, GasColor);
                    setGasColor(circle3, GasColor);
                    }
                    getMovement(groupCircle1,circle1);
                    getMovement(groupCircle2,circle2);
                    getMovement(groupCircle3,circle3);
                }
            if(!isCorrect){
                st += "реакция не идет";
            }
            lbl.setText("Products: " + st);

        });

        Pane root = new Pane(textField, btn, lbl, group, group1, group2, groupCircle1, groupCircle2, groupCircle3);
        root.setStyle("-fx-background-color: AZURE");
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Chemistry");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public void getMovement(Group group, Circle circle){
        double time = getRandom(3,5);
        TranslateTransition mov = new TranslateTransition(Duration.seconds(time), group);
        mov.setFromX(0);
        mov.setFromY(0);
        mov.setToX(0);
        mov.setToY(150.0-group.getLayoutY());
        mov.play();
        mov.setOnFinished(EventHandler ->
        {
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.TRANSPARENT);
        });
    }

    public void setGasColor(Circle circle, int GasColor) {
        switch (GasColor) {
            case 11: {
                circle.setStroke(Color.BLACK);
            }
            break;
            case 12: {
                circle.setFill(Color.DARKRED);
            }
            break;
            case 13: {
                circle.setFill(Color.DARKKHAKI);
            }
            break;
            case 14: {
                circle.setFill(Color.MEDIUMBLUE);
            }
        }
    }


    public int getSedimentColor(String A) {

        Formula[] sediments = {
                new Formula("CaC03",COLOR_SNOW),
                new Formula("BaC03",COLOR_SNOW),
                new Formula("MgC03",COLOR_SNOW),
                new Formula("PbCO3",COLOR_SNOW),
                new Formula("Ca3(PO4)2",COLOR_SNOW),
                new Formula("Mg3(PO4)2",COLOR_SNOW),
                new Formula("BaSO4",COLOR_SNOW),
                new Formula("Li3PO4",COLOR_SNOW),
                new Formula("Li2SiO3",COLOR_SNOW),
                new Formula("PbSO4",COLOR_SNOW),
                new Formula("ZnS",COLOR_SNOW),
                new Formula("AgCl",COLOR_KHAKI),
                new Formula("PbCl2",COLOR_KHAKI),
                new Formula("AgBr",COLOR_KHAKI),
                new Formula("PbBr2",COLOR_KHAKI),
                new Formula("Ag2CO3",COLOR_KHAKI),
                new Formula("AgI",COLOR_KHAKI),
                new Formula("PbI2",COLOR_KHAKI),
                new Formula("Ag3PO4",COLOR_KHAKI),
                new Formula("Cu(OH)2",COLOR_DODGERDBLUE),
                new Formula("CuS",COLOR_DARKSLATEDGREY),
                new Formula("Ag2S",COLOR_DARKSLATEDGREY),
                new Formula("PbS",COLOR_DARKSLATEDGREY),
                new Formula("Fe(OH)2",COLOR_DARKGREEN),
                new Formula("Ag2O",COLOR_MAROON),
                new Formula("HgS",COLOR_FIREBRICK),
                new Formula("MnS",COLOR_BLANCHEDALMOND)};

        for (Formula formula : sediments) {
            if (A.contains(formula.name)) {
                return formula.color;
            }
        }
        return 0;
    }


    public int getGasColor(String A){
        Formula[] gases = {
                new Formula("O2", GAS_COLOR_TRANSPARENT),
                new Formula("H2", GAS_COLOR_TRANSPARENT),
                new Formula("CO2", GAS_COLOR_TRANSPARENT),
                new Formula("N2", GAS_COLOR_TRANSPARENT),
                new Formula("NH3", GAS_COLOR_TRANSPARENT),
                new Formula("H2S", GAS_COLOR_TRANSPARENT),
                new Formula("SO2", GAS_COLOR_TRANSPARENT),
                new Formula("NO", GAS_COLOR_TRANSPARENT),
                new Formula("N2O", GAS_COLOR_TRANSPARENT),
                new Formula("NO2", GAS_COLOR_DARKRED),
                new Formula("Cl2", GAS_COLOR_DARKKHAKI),
                new Formula("O3", GAS_COLOR_MEDIUMBLUE)};

        for (Formula formula : gases) {
            if (A.contains(formula.name)&& A.length() == formula.name.length()){
                return formula.color;
            }
        }
        return 0;
    }

    public double getRandom(int min, int max){
        double random = (Math.random()*(max-min))+min;
        return random;
    }
}


