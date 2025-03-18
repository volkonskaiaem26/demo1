package com.example.demo1;

import javafx.application.Application;
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
        Rectangle rectangle = new Rectangle(60.0d, 120.0d);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        Group group = new Group(rectangle);
        btn.setOnAction(event -> {
            String text = textField.getText();
            Eclass ec = new Eclass(text);
            String a = ec.getA();
            String b = ec.getB();
            int k = WC(a);
            int c = WC(b);
            String st = "";
            st += reaction(a,b,k,c);
            int pr = osColor(st);
            if(pr==1){
                rectangle.setFill(Color.SNOW);
            }else{
                if(pr==2){
                    rectangle.setFill(Color.KHAKI);
                }else{
                    if(pr==3){
                        rectangle.setFill(Color.DODGERBLUE);
                    }else{
                        if(pr==4){
                            rectangle.setFill(Color.DARKSLATEGREY);
                        }else{
                            if(pr==5){
                                rectangle.setFill(Color.DARKGREEN);
                            }else{
                                if(pr==6){
                                    rectangle.setFill(Color.MAROON);
                                }else{
                                    if(pr==7){
                                        rectangle.setFill(Color.FIREBRICK);
                                    }else{
                                        if(pr==8){
                                            rectangle.setFill(Color.BLANCHEDALMOND);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            lbl.setText("Products:" + st);
        });
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, btn, lbl, group);
        root.setStyle("-fx-background-color: AZURE");
        Scene scene = new Scene(root, 250, 200);
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
        } else {
            if (k == 3 && c == 2) { // аналогично предыдущему, в случае если запись противоположна
                String stn2 = Neitralization(b, a);
                st += stn2;
            } else {
                if (k == 1) { // реакция для водорода и кислорода
                    if (a.contains("H2")) { // водород
                        String cs = ReactH(b, c);
                        st += cs;
                    } else {
                        if (a.contains("O2")) { // кислород
                            String cs = ReactO(b, k);
                            st += cs;
                        }
                    }
                } else {
                    if (c == 1) {
                        if (b.contains("H2")) { // аналогично для другого
                            String cs = ReactH(a, k);
                            st += cs;
                        } else {
                            if (b.contains("O2")) { // аналогично для другого
                                String cs = ReactO(a, k);
                                st += cs;
                            }
                        }
                    } else {
                        if (k == 4 && c == 4) {
                            String result = sreaction(a, b);
                            st += result;
                        }
                    }
                }
            }
        }
        return st;
    }

    public int WC(String a) {    // определение класса вещества
        int l = 0;
        if (a.contains("OH")) {
            l = 3;
        } else {
            if (a.contains("H")) {
                if (a.contains("H2") && a.length() == 2) {
                    l = 1;
                } else {
                    if (a.indexOf("H") == 0) {
                        if (a.contains("H2O")) {
                            l = 7;
                        } else {
                            l = 2;
                        }
                    } else {
                        l = 5;
                    }
                }
            } else {
                if (a.length() == 1) {
                    l = 1;
                } else {
                    if (a.length() == 2) {
                        if (a.contains("2") || ((int) a.charAt(1) <= 122 && (int) a.charAt(1) >= 97)) {
                            l = 1;
                        } else {
                            if (!a.contains("O")) {
                                l = 4;
                            } else {
                                if (a.indexOf("O") == a.length() - 1) {
                                    l = 6;
                                } else {
                                    l = 4;
                                }
                            }
                        }
                    } else {
                        if (!a.contains("O")) {
                            l = 4;
                        } else {
                            if (a.indexOf("O") == a.length() - 1) {
                                l = 6;
                            } else {
                                l = 4;
                            }
                        }
                    }
                }
            }
        }
        return l;
    }

    public String getIons(String A, int a) { // разделение вещества на ионы (пока лишь для простых веществ, кислот и оснований).
        String ion1 = "";
        String ion2 = "";
        String ions = "";
        if (a == 1) {
            ion1 += A;
        } else {
            if (a == 2) {
                int h = 1;
                int k = 1;
                if ((int) A.charAt(1) >= 48 && (int) A.charAt(1) <= 57) {
                    h = (int) A.charAt(1) - 48;
                    k = 2;
                }
                ion1 += h + "H";
                for (int i = k; i < A.length(); i++) {
                    ion2 += A.charAt(i);
                }
            } else {
                if (a == 3) {
                    int h1 = 1;
                    if ((int) A.charAt(A.length() - 1) >= 48 && (int) A.charAt(A.length() - 1) <= 57) {
                        h1 = (int) A.charAt(A.length() - 1) - 48;
                    }
                    ion2 += h1 + "OH";
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
            }
        }
        ions += ion1;
        if (a != 1) {
            ions += ";";
            ions += ion2;
        }
        return ions;
    }

    public String Neitralization(String A, String B) {
        String product1 = "";
        String product2 = "";
        int k = 2;
        int c = 3;
        String ionsA = getIons(A, k);
        String ionsB = getIons(B, c);
        String[] Ai = ionsA.split(";");
        String[] Bi = ionsB.split(";");
        int Vk = (int) Bi[1].charAt(0) - 48;
        int Va = (int) Ai[0].charAt(0) - 48;
        Calcval p = new Calcval(Va,Vk);
        Va = p.getA();
        Vk = p.getB();
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
        String product = product1 + "+" + product2;
        return product;
    }

    public String Reaction(String A, String B, int k, int c) {
        String product1 = "";
        if (k == 2 && c == 3) {// соль+кислота реакция нейтрализации
            String stn1 = Neitralization(A, B);
            product1 += stn1;
        } else {
            if (k == 3 && c == 2) { // аналогично предыдущему, в случае если запись противоположна
                String stn2 = Neitralization(B, A);
                product1 += stn2;
            } else {
                if (k == 1) { // реакция для водорода и кислорода
                    if (A.contains("H2")) { // водород
                        String cs = ReactH(B, c);
                        product1 += cs;
                    } else {
                        if (A.contains("O2")) { // кислород
                            String cs = ReactO(B, k);
                            product1 += cs;
                        }
                    }
                } else {
                    if (c == 1) {
                        if (B.contains("H2")) { // аналогично для другого
                            String cs = ReactH(A, k);
                            product1 += cs;
                        } else {
                            if (B.contains("O2")) { // аналогично для другого
                                String cs = ReactO(A, k);
                                product1 += cs;
                            }
                        }
                    } else {
                        if (k == 4 && c == 4) {
                            String result = sreaction(A, B);
                            product1 += result;
                        }
                    }
                }
            }
        }
        return product1;
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
            if (l == 0) {
                product += "реакция не идет";
            } else {
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
                                            for (int i = 0; i < (A.length() - 1); i++) {
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
        } else {
            if (k == 6) {
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
            if (l == 0) {
                product += "реакция не идет";
            } else {
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
        } else {
            if (k == 5) {
                if (A.contains("NH3")) {
                    product += "N2+H2O";
                } else {
                    if (A.contains("H2S")) {
                        product += "SO2+H2O";
                    }
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


