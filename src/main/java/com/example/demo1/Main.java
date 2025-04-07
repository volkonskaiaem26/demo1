package com.example.demo1;

public class Main {

    String A;
    String B;
    
    Main(String a,String b){
        this.A = a;
        tnis.B = b;
    }
    
    public String reaction(){
        String a = this.A;
        String b = this.B;
        int classA = getClass(a);
        int classB = getClass(b)
        String st = "";
        if (classA == 2 && classB == 3) {// соль+кислота реакция нейтрализации
            String stn1 = Neitralization(a, b);
            st += stn1;
        } else if (classA == 3 && classB == 2) { // аналогично предыдущему, в случае если запись противоположна
                String stn2 = Neitralization(b, a);
                st += stn2;
        } else if (classA == 1) { // реакция для водорода и кислорода
            if (a.contains("H2")) { // водород
                String cs = ReactH(b);
                st += cs;
            } else if (a.contains("O2")) { // кислород
                            String cs = ReactO(b);
                            st += cs;
                        }
        } else if (classB == 1) {
            if (b.contains("H2")) { // аналогично для другого
                String cs = ReactH(a);
                st += cs;
            } else if (b.contains("O2")) { // аналогично для другого
                                String cs = ReactO(a);
                                st += cs;
                            }
        } else if (classA == 4 && classA == 4) {
            String result = sreaction(a, b);
            st += result;
        }
        return st;
    }

    public int getClass(String A){    // определение класса вещества
        int elClass = 0;
        if (A.contains("OH")) {
            elClass = 3;
        } else {
            if (a.contains("H")) {
                if (A.contains("H2") && A.length() == 2) {
                    elClass = 1;
                } else if (A.indexOf("H") == 0) {
                    if (A.contains("H2O")) {
                        elClass = 7;
                    } else {
                        elClass = 2;
                    }
                } else {
                    elClass = 5;
                }
            } else {
                if (A.length() == 1) {
                    elClass = 1;
                } else if (A.length() == 2) {
                    if (A.contains("2") || ((int) A.charAt(1) <= 122 && (int) A.charAt(1) >= 97)) {
                        elClass = 1;
                    } else if (!A.contains("O")) {
                        elClass = 4;
                    } else if (A.indexOf("O") == A.length() - 1) {
                        elClass = 6;
                    }
                } else {
                    if (!A.contains("O")) {
                        elClass = 4;
                    } else if (A.indexOf("O") == A.length() - 1) {
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
        int typeReagent = 0;
        if (A.length() == 1) {
            if (A.contains("S")) {
                typeReagent = 3;
            } else {
                if (A.contains("C")) {
                    typeReagent = 5;
                }
            }
        } else {
            if (A.contains("2")) {
                if (A.contains("O")) {
                    typeReagent = 6;
                } else {
                    if (A.contains("F") || A.contains("Cl") || A.contains("Br") || A.contains("I")) {
                        typeReagent = 7;
                    } else {
                        if (A.contains("N")) {
                            typeReagent = 4;
                        }
                    }
                }
            } else {
                if (A.contains("Ca") || A.contains("Sr") || A.contains("Ba") || A.contains("Ra")) {
                    typeReagent = 2;
                } else {
                    if (A.contains("Na") || A.contains("K") || A.contains("Li") || A.contains("Rb") || A.contains("Cs") || A.contains("Fr")) {
                        typeReagent = 1;
                    }
                }
            }
        }
        return typeReagent;
    }


    public String ReactH(String A) { // реакции для водорода
        String product = "";
        int classA = getClass(A)
        if (classA == 1) {
            int typeReagent = ReagH(A);
            switch(typeReagent){
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
        } else if (classA == 6) {
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

    public String ReactO(String A) { // реакции для кислорода
        String product = "";
        int classA = getClass(A);
        if (k == 1) {
            int TypeReagent = ReagO(A);
            switch (TypeReagent) {
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
            if (classA == 5) {
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
}