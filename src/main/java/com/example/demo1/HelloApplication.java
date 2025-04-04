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
            int k = getClass(a);
            int c = getClass(b);
            String st = "";
            st += reaction(a,b,k,c);
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

    public String reaction(String a,String b, int k,int c){
        String st = "";
        if (k == 2 && c == 3) {// соль+кислота реакция нейтрализации
            String stn1 = Neitralization(a, b);
            st += stn1;
        } else if (k == 3 && c == 2) { // аналогично предыдущему, в случае если запись противоположна
                String stn2 = Neitralization(b, a);
                st += stn2;
        } else if (k == 1) { // реакция для водорода и кислорода
            if (a.contains("H2")) { // водород
                String cs = ReactH(b, c);
                st += cs;
            } else if (a.contains("O2")) { // кислород
                            String cs = ReactO(b, k);
                            st += cs;
                        }
        } else if (c == 1) {
            if (b.contains("H2")) { // аналогично для другого
                String cs = ReactH(a, k);
                st += cs;
            } else if (b.contains("O2")) { // аналогично для другого
                                String cs = ReactO(a, k);
                                st += cs;
                            }
        } else if (k == 4 && c == 4) {
            String result = sreaction(a, b);
            st += result;
        }
        return st;
    }

    public int getClass(String a) {    // определение класса вещества
        int elClass = 0;
        if (a.contains("OH")) {
            elClass = 3;
        } else {
            if (a.contains("H")) {
                if (a.contains("H2") && a.length() == 2) {
                    elClass = 1;
                } else if (a.indexOf("H") == 0) {
                    if (a.contains("H2O")) {
                        elClass = 7;
                    } else {
                        elClass = 2;
                    }
                } else {
                    elClass = 5;
                }
            } else {
                if (a.length() == 1) {
                    elClass = 1;
                } else if (a.length() == 2) {
                    if (a.contains("2") || ((int) a.charAt(1) <= 122 && (int) a.charAt(1) >= 97)) {
                        elClass = 1;
                    } else if (!a.contains("O")) {
                        elClass = 4;
                    } else if (a.indexOf("O") == a.length() - 1) {
                        elClass = 6;
                    }
                } else {
                    if (!a.contains("O")) {
                        elClass = 4;
                    } else if (a.indexOf("O") == a.length() - 1) {
                        elClass = 6;
                    } else {
                        elClass = 4;
                    }
                }
            }
        }
        return elClass;
    }

    public String getIons(String A) { // разделение вещества на ионы (пока лишь для простых веществ, кислот и оснований).
        int elClass = getClass(A);
        String ion1 = "";
        String ion2 = "";
        String ions = "";
        if (elClass == 1) {
            ion1 += A;
        } else if (elClass == 2) {
            int valOfAnion = 1;
            int indexOfAnion = 1;
            if ((int) A.charAt(1) >= 48 && (int) A.charAt(1) <= 57) {
                valOfAnion = (int) A.charAt(1) - 48;
                indexOfAnion = 2;
            }
            ion1 += valOfAnion + "H";
            for (int i = indexOfAnion; i < A.length(); i++) {
                ion2 += A.charAt(i);
            }
        } else if (elClass == 3) {
            int valOfKation = 1;
            if ((int) A.charAt(A.length() - 1) >= 48 && (int) A.charAt(A.length() - 1) <= 57) {
                valOfKation = (int) A.charAt(A.length() - 1) - 48;
            }
            ion2 += valOfKation + "OH";
            if (A.contains(")")) {
                int c = A.indexOf("(");
                for (int i = 0; i < c; i++) {
                    ion1 += A.charAt(i);
                }
            } else {
                int c1 = A.indexOf("O");
                for (int i = 0; i < c1; i++) {
                    ion1 += A.charAt(i);
                }
            }
        }
        ions += ion1;
        if (elClass != 1) {
            ions += ";";
            ions += ion2;
        }
        return ions;
    }

    public String Neitralization(String A, String B) {
        String product1 = "";
        String product2 = "";
        String ionsA = getIons(A, k);
        String ionsB = getIons(B, c);
        String[] AIons = ionsA.split(";");
        String[] BIons = ionsB.split(";");
        int valKat = (int) BIons[1].charAt(0) - 48;
        int valAn = (int) AIons[0].charAt(0) - 48;
        Calcval p = new Calcval(valAn,valKat);
        valAn = p.getA();
        valAn = p.getB();
        String Ap = "";
        if (AIons[1].length() == 1) {
            AProducts += AIons[1];
            if (valKat != 1) {
                AProducts += valKat;
            }
        } else {
            if (valKat != 1) {
                AProducts += "(";
                AProducts += AIons[1];
                AProducts += ")";
                AProducts += valKat;
            } else {
                AProducts += AIons[1];
            }
        }
        String BProducts = "";
        BProducts += BIons[0];
        if (valAn != 1) {
            BProducts += valAn;
        }
        product1 += BProducts + AProducts;
        product2 += "H2O";
        String product = product1 + "+" + product2;
        return product;
    }

    public int ReagH(String A) { //определение более точной классификации вещества
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
                    } else {
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


    public String ReactH(String A, int k) { // реакции для водорода
        String product = "";
        if (k == 1) {
            int l = ReagH(A);
            switch(l){
                case 0: product += "реакция не идет";
                case 1: {
                    product += A;
                    product += "H";
                }
                case 2: {
                    product += A;
                    product += "H2";
                }
                case 3: product += "H2S";
                case 4: product += "NH3";
                case 5: product += "CH4";
                case 6: product += "H2O";
                case 7: {
                    String a1 = "";
                    for (int i = 0; i < (A.length() - 1); i++) {
                        a1 += A.charAt(i);
                    }
                    product += "H";
                    product += a1;
                }
            }
        } else if (k == 6) {
                int n = A.indexOf("O");
                String a2 = "";
                for (int i = 0; i < n; i++) {
                    a2 += A.charAt(i);
                }
                product += a2;
                product += "+H2O";
            } else {
                product += "реакция не идет";
            }
        return product;
    }

    public int ReagO(String A) {
        int l = 0;
        if (A.length() == 1) {
            if (A.contains("S")) {
                l = 2;
            } else {
                if (A.contains("C")) {
                    l = 6;
                } else {
                    if (A.contains("P")) {
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
                if (A.contains("Si")) {
                    l = 3;
                } // для написания реакций с металлами необходимо создать таблицу валентностей, иначе будет неверно
            }
        }
        return l;
    }

    public String ReactO(String A, int k) { // реакции для кислорода
        String product = "";
        if (k == 1) {
            int l = ReagO(A);
            switch (l) {
                case 0:
                    product += "реакция не идет";
                case 1:
                    product += "OF2";
                case 2:
                    product += "SO2";
                case 3:
                    product += "SiO2";
                case 4:
                    product += "P2O5";
                case 5:
                    product += "NO";
                case 6:
                    product += "CO2";
            }
        }else {
            if (k == 5) {
                if (A.contains("NH3")) {
                    product += "N2+H2O";
                } else if (A.contains("H2S")) {
                        product += "SO2+H2O";
                }
            } else {
                product += "реакция не идет";
            } //некоторые другие реакции не учитываются
        }
        return product;
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


