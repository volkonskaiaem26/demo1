package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


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

    int GAS_COLOR_TRANSPARENT = 1;
    int GAS_COLOR_DARKRED = 2;
    int GAS_COLOR_DARKKHAKI = 3;
    int GAS_COLOR_MEDIUMBLUE = 4;


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

        btn.setOnAction(event -> {
            String text = textField.getText();
            String st = "";
            if(text!=null){
               Eclass ec = new Eclass(text);
               String a = ec.getA();
               String b = ec.getB();
               Main main = new Main(a,b);
               st += main.reaction();
               int SedimentColor = getSedimentColor(st);
               switch(pr){
                   case COLOR_SNOW: rectangle2.setFill(Color.SNOW);
                   case COLOR_KHAKI: rectangle2.setFill(Color.KHAKI);
                   case COLOR_DODGERDBLUE: rectangle2.setFill(Color.DODGERBLUE);
                   case COLOR_DARKSLATEDGREY: rectangle2.setFill(Color.DARKSLATEGREY);
                   case COLOR_DARKGREEN: rectangle2.setFill(Color.DARKGREEN);
                   case COLOR_MAROON: rectangle2.setFill(Color.MAROON);
                   case COLOR_FIREBRICK: rectangle2.setFill(Color.FIREBRICK);
                   case COLOR_BLANCHEDALMOND: rectangle2.setFill(Color.BLANCHEDALMOND);
               }
            }else{
               st += "реакция не идет";
            }
            lbl.setText("Products: " + st);
        });

        Pane root = new Pane(textField, btn, lbl, group, group1, group2);
        root.setStyle("-fx-background-color: AZURE");
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Chemistry");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    public int getSedimentColor(String A) {

        Formula[] formulas = {new Formula("CaC03",COLOR_SNOW), new Formula("BaC03",COLOR_SNOW), new Formula("MgC03",COLOR_SNOW),
new Formula("PbCO3",COLOR_SNOW), new Formula("Ca3(PO4)2",COLOR_SNOW), new Formula("Mg3(PO4)2",COLOR_SNOW), new Formula("BaSO4",COLOR_SNOW),
new Formula("Li3PO4",COLOR_SNOW), new Formula("Li2SiO3",COLOR_SNOW), new Formula("PbSO4",COLOR_SNOW), new Formula("ZnS",COLOR_SNOW),
new Formula("AgCl",COLOR_KHAKI), new Formula("PbCl2",COLOR_KHAKI), new Formula("AgBr",COLOR_KHAKI), new Formula("PbBr2",COLOR_KHAKI),
new Formula("Ag2CO3",COLOR_KHAKI), new Formula("AgI",COLOR_KHAKI), new Formula("PbI2",COLOR_KHAKI), new Formula("Ag3PO4",COLOR_KHAKI),
new Formula("Cu(OH)2",COLOR_DODGERDBLUE), new Formula("CuS",COLOR_DARKSLATEDGREY), new Formula("Ag2S",COLOR_DARKSLATEDGREY),
new Formula("PbS",COLOR_DARKSLATEDGREY), new Formula("Fe(OH)2",COLOR_DARKGREEN), new Formula("Ag2O",COLOR_MAROON), new Formula("HgS",COLOR_FIREBRICK),
new Formula("MnS",COLOR_BLANCHEDALMOND)};

        for (Formula formula : formulas) {
            if (A.contains(formula.name)) {
                return formula.color;
            }
        }
        return 0;
    }


    public int gasColor(String A){
        Formula[] gases = {new Formula("O2",GAS_COLOR_TRANSPARENT), new Formula("H2", GAS_COLOR_TRANSPARENT), new Formula("CO2", GAS_COLOR_TRANSPARENT),
new Formula("N2", GAS_COLOR_TRANSPARENT), new Formula("NH3", GAS_COLOR_TRANSPARENT), new Formula("H2S", GAS_COLOR_TRANSPARENT), 
new Formula("SO2", GAS_COLOR_TRANSPARENT), new Formula("NO", GAS_COLOR_TRANSPARENT), new Formula("N2O", GAS_COLOR_TRANSPARENT), 
new Formula("NO2", GAS_COLOR_DARKRED), new Formula("Cl2", GAS_COLOR_DARKKHAKI), new Formula("O3", GAS_COLOR_MEDIUMBLUE)};

        for (Formula formula : formulas) {
            if (A.contains(formula.name)){
                return formula.color;
            }
        }
        return 0;
    }
}


