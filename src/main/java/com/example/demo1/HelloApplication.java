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
            Eclass ec = new Eclass(text);
            String a = ec.getA();
            String b = ec.getB();
            Main main = new Main(a,b);
            String st = "";
            st += main.reaction();
            int pr = osColor(st);
            switch(pr){
                case 1: rectangle2.setFill(Color.SNOW);
                case 2: rectangle2.setFill(Color.KHAKI);
                case 3: rectangle2.setFill(Color.DODGERBLUE);
                case 4: rectangle2.setFill(Color.DARKSLATEGREY);
                case 5: rectangle2.setFill(Color.DARKGREEN);
                case 6: rectangle2.setFill(Color.MAROON);
                case 7: rectangle2.setFill(Color.FIREBRICK);
                case 8: rectangle2.setFill(Color.BLANCHEDALMOND);
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


    public int osColor(String A) {
        Formula[] formulas = {new Formula("CaC03",1),new Formula("BaC03",1) ,new Formula("Mg03",1) ,new Formula("PbC03",1) ,new Formula("Ca3(PO4)2",1) ,new Formula("Mg3(PO4)2",1) ,new Formula("BaSO4",1) ,new Formula("Li3PO4",1) ,new Formula("Li2SiO3",1) ,new Formula("PbSO4",1) ,new Formula("ZnS",1) ,new Formula("AgCl",1) ,new Formula("PbCl2",1) ,new Formula("AgBr",2) ,new Formula("PbBr2",2) ,new Formula("Ag2CO3",2) ,new Formula("AgI",2) ,new Formula("PbI2",2) ,new Formula("Ag3PO4",2) ,new Formula("Cu(OH)2",3) ,new Formula("CuS",4) ,new Formula("Ag2S",4) ,new Formula("PbS",4) ,new Formula("Fe(OH)2",5) ,new Formula("Ag2O",6) ,new Formula("HgS",7),new Formula("MnS",8)  };
        //String[] elements = {"CaCO3","BaCO3","MgCO3","PbCO3","Ca3(PO4)2","Ba3(PO4)2","Mg3(PO4)2","BaSO4","Li3PO4","PbSO4","ZnS","AgCl","Mg(OH)2","Zn(OH)2","Be(OH)2","Al(OH)3","AgBr","PbBr2","Ag2CO3","AgI","PbI2","Ag3PO4","Cu(OH)2","CuS","Ag2S","PbS","Fe(OH)2","HgS","MnS"};
        //int[] colors = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,3,4,4,4,5,6,7};
        for (Formula formula : formulas) {
            if (A.contains(formula.name)) {
                return formula.color;
            }
        }
        return 0;
    }

    public int gasColor(String A){
        Formula[] gases = {new Formula("O2",0), new Formula("H2", 0), new Formula("CO2", 0), new Formula("N2", 0), new Formula("NH3", 0), new Formula("H2S", 0), new Formula("SO2", 0), new Formula("NO", 0), new Formula("N2O", 0), new Formula("NO2", 1), new Formula("Cl2", 2), new Formula("O3", 3)};
        for (Formula formula : formulas) {
            if (A.contains(formula.name)){
                return formula.color;
            }
        }
        return 0;
    }
}


