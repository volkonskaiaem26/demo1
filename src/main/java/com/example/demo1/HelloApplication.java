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

    
    public String sreaction(String A, String B) {
        String product1 = "";
        String product2 = "";
        String product = "";
        int[][] table1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 2, 2, 1, 0}, {0, 0, 0, 0, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1}};
        Anions[] an = {new Anions("NO3", 1), new Anions("F", 1), new Anions("Cl", 1), new Anions("Br", 1), new Anions("I", 1), new Anions("SO3", 2),new Anions("SO4", 2),new Anions("CO3", 2),new Anions("SiO3", 2),new Anions("NO3", 1),new Anions("PO4", 3),new Anions("CrO4", 2)};
        kations [] kat = {new kations("Li", 1),new kations("NH4", 1),new kations("K", 1),new kations("Na", 1),new kations("Ag", 1),new kations("Ba", 2),new kations("Ca", 2),new kations("Mg", 2),new kations("Zn", 2),new kations("Mn", 2),new kations("Cu", 2),new kations("Hg", 2),new kations("Pb", 2),new kations("Fe", 2),new kations("Al", 3),new kations("Cr", 3),new kations("Bi", 3),new kations("Sn", 2),new kations("Sr", 2)};
        String kationA = "";
        String anionA = "";
        String kationB = "";
        String anionB = "";
        int kA = 0;
        int aA = 0;
        int kB = 0;
        int aB = 0;
        for (int i = 0; i < kat.length; i++) {
            if (A.contains(kat[i].kation)) {
                kA = i;
            }
            if (B.contains(kat[i].kation)) {
                kB = i;
            }
        }
        for (int i = 0; i < an.length; i++) {
            if (A.contains(an[i].anion)) {
                aA = i;
            }
            if (B.contains(an[i].anion)) {
                aB = i;
            }
        }
        int ap = table1[aA][kB];
        int bp = table1[aB][kA];
        int r = 0;
        if (ap + bp > 0) {
            r = 1;
        }
        if (r == 1) {
            kationA += kat[kA].kation;
            anionA += an[aA].anion;
            kationB += kat[kB].kation;
            anionB += an[aB].anion;
            int valkA = kat[kA].val;
            int valaA = an[aA].val;
            int valkB = kat[kB].val;
            int valaB = an[aB].val;
            product1 += kationA;
            Calcval p1 = new Calcval(valkA,valaB);
            Calcval p2 = new Calcval(valkB,valaA);
            valkA = p1.getA();
            valaB = p1.getB();
            valkB = p2.getA();
            valaA = p2.getB();
            if(valaB!=1) {
                product1 += valaB;
            }
            if(valkA!=1) {
                product1 += "(" + anionB + ")" + valkA;
            }else{
                product1 += anionB;
            }
            product2 += kationB;
            if(valaA!=1) {
                product2 += valaA;
            }
            if(valkB!=1) {
                product2 += "(" + anionA + ")" + valkB;
            }else{
                product2 += anionA;
            }
            product += product1 + "+" + product2;
        }else{
            product += "реакция не идет";
        }
        return product;
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
}


