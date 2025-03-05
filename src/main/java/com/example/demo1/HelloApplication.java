package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException { //создание окна программы
        Label lbl = new Label();
        TextField textField = new TextField();
        textField.setPrefColumnCount(11);
        Button btn = new Button("Start");
        btn.setOnAction(event -> {
            String text = textField.getText();
            Eclass ec = new Eclass(text);
            String a = ec.getA();
            String b = ec.getB();
            int k = WC(a);
            int c = WC(b);
            lbl.setText("Products: " + Reaction(a, b, k, c));
        });
        Rectangle rectangle = new Rectangle(60.0d, 120.0d);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        Group group = new Group(rectangle);
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, btn, lbl, group);
        Scene scene = new Scene(root, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Chemistry");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public int WC(String a){    // определение класса вещества
        int l = 0;
        if(a.contains("OH")){
            l = 3;
        }else{
            if(a.contains("H")){
                if(a.length() == 2 && a.contains("2")){
                    l = 1;
                }else{
                    if(a.indexOf("H")==1){
                        if(a.contains("H2O")){
                            l = 7;
                        }else{
                            l=2;
                        }
                    }else{
                        l = 5;
                    }
                }
            }else{
                if(a.length() == 1){
                    l = 1;
                }else{
                    if(a.length() == 2){
                        if(a.contains("2") ||((int) a.charAt(1) <= 122 && (int) a.charAt(1)>=97)){
                            l = 1;
                        }else{
                            if(!a.contains("O")){
                                l = 4;
                            }else{
                                if(a.indexOf("O")==a.length()-1){
                                    l = 6;
                                }else{
                                    l = 4;
                                }
                            }
                        }
                    }
                }
            }
        }
        return l;
    }

    public String getIons(String A, int a){ // разделение вещества на ионы (пока лишь для простых веществ, кислот и оснований).
        String ion1 = "";
        String ion2 = "";
        String ions = "";
        if(a==1){
            ion1 += A;
        }else{
            if(a==2){
                int h = 1;
                if((int) A.charAt(1)>=48 && (int) A.charAt(1) <=57) {
                    h = (int) A.charAt(1) - 48;
                }
                ion1 += h +"H";
                for(int i=2;i<A.length();i++){
                    ion2 += A.charAt(i);
                }
            }else{
                if(a==3){
                    int h1 = 1;
                    if((int) A.charAt(A.length()-1)>=48 && (int) A.charAt(A.length()-1) <=57) {
                        h1 = (int) A.charAt(A.length()-1) - 48;
                    }
                    ion2 += h1 + "OH";
                    if(A.contains(")")){
                        int c = A.indexOf("(");
                        for(int i=0;i<c;i++){
                            ion1 += A.charAt(i);
                        }
                    }else{
                        int c1 = A.indexOf("O");
                        for(int i=0;i<c1;i++){
                            ion1 += A.charAt(i);
                        }
                    }
                }
            }
        }
        ions += ion1;
        if(a!=1){
            ions += ";";
            ions += ion2;
        }
        return ions;
    }
    public String Reaction(String A, String B, int k, int c) {
        String product1 = "";
        String product2 = "";
        String ionsA = getIons(A, k);
        String ionsB = getIons(B, c);
        int r = 0;
        if (k == 2 && c == 3) { // соль+кислота реакция нейтрализации
            String[] Ai = ionsA.split(";");
            String[] Bi = ionsB.split(";");
            int Vk = (int) Bi[1].charAt(0) - 48;
            int Va = (int) Ai[0].charAt(0) - 48;
            if (Va % Vk == 0) {
                Vk = 1;
                Va = Va / Vk;
            } else {
                if (Vk % Va == 0) {
                    Va = 1;
                    Vk = Vk / Va;
                }
            }
            String Ap = "";
            if (Ai[1].length() == 1) {
                Ap += Ai[1];
                if (Vk != 1) {
                    Ap += Vk;
                }
            } else {
                if (Vk != 1) {
                    Ap += "(";
                    Ap += Ai[1];
                    Ap += ")";
                    Ap += Vk;
                } else {
                    Ap += Ai[1];
                }
            }
            String Bp = "";
            Bp += Bi[0];
            if (Va != 1) {
                Bp += Va;
            }
            product1 += Bp+Ap;
            product2 += "H2O";
        } else {
            if (k == 3 && c == 2) { // аналогично предыдущему, в случае если запись противоположна
                String[] Ai = ionsB.split(";");
                String[] Bi = ionsA.split(";");
                int Vk = (int) Bi[1].charAt(0) - 48;
                int Va = (int) Ai[0].charAt(0) - 48;
                if (Va % Vk == 0) {
                    Vk = 1;
                    Va = Va / Vk;
                } else {
                    if (Vk % Va == 0) {
                        Va = 1;
                        Vk = Vk / Va;
                    }
                }
                String Ap = "";
                if (Ai[1].length() == 1) {
                    Ap += Ai[1];
                    if (Vk != 1) {
                        Ap += Vk;
                    }
                } else {
                    if (Vk != 1) {
                        Ap += "(";
                        Ap += Ai[1];
                        Ap += ")";
                        Ap += Vk;
                    } else {
                        Ap += Ai[1];
                    }
                }
                String Bp = "";
                Bp += Bi[0];
                if (Va != 1) {
                    Bp += Va;
                }
                product1 += Bp + Ap;
                product2 += "H2O";
            } else {
                if (k == 1) { // реакция для водорода и кислорода
                    if (A.contains("H2")) { // водород
                        String cs = ReactH(B, c);
                        product1 += cs;
                        r = 1;
                    }else{
                        if (A.contains("O2")) { // кислород
                            String cs = ReactO(B, k);
                            product1 += cs;
                            r = 1;
                        }
                    }
                } else {
                    if (c == 1) {
                        if (B.contains("H2")) { // аналогично для другого
                            String cs = ReactH(A, k);
                            product1 += cs;
                            r = 1;
                        } else {
                            if (B.contains("O2")) { // аналогично для другого
                                String cs = ReactO(A, k);
                                product1 += cs;
                                r = 1;
                            }
                        }
                    }
                }
            }
        }
        String product = "";
        product += product1;
        if (r == 0) {
            product += "+";
            product += product2;
        }
        return product;
    }
    public int ReagH(String A){ //определение более точной классификации вещества
        int l = 0;
        if (A.length() == 1) {
            if (A.contains("S")) {
                l = 3;
            } else {
                    if (A.contains("C")) {
                        l = 5;
                    }
                }
            } else {
            if (A.contains("2")) {
                if (A.contains("O")) {
                    l = 6;
                } else {
                    if (A.contains("F") || A.contains("Cl") || A.contains("Br") || A.contains("I")) {
                        l = 7;
                    } else{
                        if (A.contains("N")) {
                            l = 4;
                        }
                    }
                }
            } else {
                if (A.contains("Ca") || A.contains("Sr") || A.contains("Ba") || A.contains("Ra")) {
                    l = 2;
                } else {
                    if (A.contains("Na") || A.contains("K") || A.contains("Li") || A.contains("Rb") || A.contains("Cs") || A.contains("Fr")) {
                        l = 1;
                    }
                }
            }
        }
        return l;
    }


    public String ReactH(String A,int k){ // реакции для водорода
        String product = "";
        if(k==1){
            int l = ReagH(A);
            if(l==0){
                product += "реакция не идет";
            }else {
                if (l == 1) {
                    product += A;
                    product += "H";
                } else {
                    if (l == 2) {
                        product += A;
                        product += "H2";
                    } else {
                        if (l == 3) {
                            product += "H2S";
                        } else {
                            if (l == 4) {
                                product += "NH3";
                            } else {
                                if (l == 5) {
                                    product += "CH4";
                                } else {
                                    if (l == 6) {
                                        product += "H2O";
                                    } else {
                                        if (l == 7) {
                                            String a1 = "";
                                            for (int i=0; i < (A.length() - 1); i++) {
                                                a1 += A.charAt(i);
                                            }
                                            product += "H";
                                            product += a1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            if(k == 6){
                int n = A.indexOf("O");
                String a2 = "";
                for(int i = 0; i<n; i++){
                    a2 += A.charAt(i);
                }
                product += a2;
                product += "+H2O";
            }else{
                product += "реакция не идет";
            }
        }
        return product;
    }

    public int ReagO(String A){
        int l = 0;
        if (A.length() == 1) {
            if (A.contains("S")) {
                l = 2;
            } else {
                if (A.contains("C")) {
                    l = 6;
                }else{
                    if(A.contains("P")){
                        l = 4;
                    }
                }
            }
        } else {
            if (A.contains("2")) {
                if (A.contains("H")) {
                    l = 6;
                } else {
                    if (A.contains("N")) {
                        l = 5;
                    }
                }
            } else {
                if (A.contains("Si")){
                    l = 3;
                } // для написания реакций с металлами необходимо создать таблицу валентностей, иначе будет неверно
            }
        }
        return l;
    }

    public String ReactO(String A,int k){ // реакции для водорода
        String product = "";
        if(k==1){
            int l = ReagO(A);
            if(l==0){
                product += "реакция не идет";
            }else {
                if (l == 1) {
                    product += "OF2";
                } else {
                    if (l == 2) {
                        product += "SO2";
                    } else {
                        if (l == 3) {
                            product += "SiO2";
                        } else {
                            if (l == 4) {
                                product += "P2O5";
                            } else {
                                if (l == 5) {
                                    product += "NO";
                                } else {
                                    if (l == 6) {
                                        product += "CO2";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            if(k == 5){
                if(A.contains("NH3")){
                    product += "N2+H2O";
                }else{
                    if(A.contains("H2S")){
                        product += "SO2+H2O";
                    }
                }
            }else{
                product += "реакция не идет";
            } //некоторые другие реакции не учитываются
        }
        return product;
    }
}
