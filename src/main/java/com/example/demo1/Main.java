package com.example.demo1;

public class Main {

    int EL_SIMPLE = 1;
    int EL_ACID = 2;
    int EL_ALKALI = 3;
    int EL_SALT = 4;
    int EL_BIN_H = 5;
    int EL_OXIDE = 6;
    int EL_WATER = 7;

    int[][] TABLE = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 2, 2, 1, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1}};

    Anions[] ANIONS = {
            new Anions("NO3", 1), 
            new Anions("F", 1), 
            new Anions("Cl", 1),         
            new Anions("Br", 1), 
            new Anions("I", 1), 
            new Anions("SO3", 2),
            new Anions("SO4", 2),
            new Anions("CO3", 2),
            new Anions("SiO3", 2),
            new Anions("NO3", 1),
            new Anions("PO4", 3),
            new Anions("CrO4", 2)};
    Kations [] KATIONS = {
            new Kations("Li", 1),
            new Kations("NH4", 1),
            new Kations("K", 1),
            new Kations("Na", 1),
            new Kations("Ag", 1),
            new Kations("Ba", 2),
            new Kations("Ca", 2),
            new Kations("Mg", 2),
            new Kations("Zn", 2),
            new Kations("Mn", 2),
            new Kations("Cu", 2),
            new Kations("Hg", 2),
            new Kations("Pb", 2), 
            new Kations("Fe", 2),
            new Kations("Al", 3),
            new Kations("Cr", 3),
            new Kations("Bi", 3),
            new Kations("Sn", 2),
            new Kations("Sr", 2)};

    String A;
    String B;
    
    Main(String a,String b){
        this.A = a;
        this.B = b;
    }
    
    public String reaction(){
        String a = this.A;
        String b = this.B;
        int classA = getClass(a);
        int classB = getClass(b);
        String st = "";

        if (classA == EL_ACID && classB == EL_ALKALI) {
            String stn1 = Neitralization(a, b);
            st += stn1;
        }

        if (classA == EL_ALKALI && classB == EL_ACID) {
            String stn2 = Neitralization(b, a);
            st += stn2;
        }

        if (classA == EL_SIMPLE) {
            if (a.contains("H2")) { // водород
                String cs = getReactionH(b);
                st += cs;
            } else if (a.contains("O2")) { // кислород
                String cs = getReactionO(b);
                st += cs;
            }
        }

        if (classB == EL_SIMPLE) {
            if (b.contains("H2")&&!a.contains("O2")) {
                String cs = getReactionH(a);
                st += cs;
            } else if (b.contains("O2")&&!a.contains("H2")) {
                String cs = getReactionO(a);
                st += cs;
            }
        }

        if (classA == EL_SALT && classB == EL_SALT) {
            String result = sreaction(a, b);
            st += result;
        }
        return st;
    }

    public int getClass(String A){    // определение класса вещества
        int elClass = 0;
        if (A.contains("OH")) {
            elClass = EL_ALKALI;
        } else {
            if (A.contains("H")) {
                if (A.contains("H2") && A.length() == 2) {
                    elClass = EL_SIMPLE;
                } else if (A.indexOf("H") == 0) {
                    if (A.contains("H2O")) {
                        elClass = EL_WATER;
                    } else {
                        elClass = EL_ACID;
                    }
                } else {
                    elClass = EL_BIN_H;
                }
            } else {
                if (A.length() == 1) {
                    elClass = EL_SIMPLE;
                } else if (A.length() == 2) {
                    if (A.contains("2") || ((int) A.charAt(1) <= 122 && (int) A.charAt(1) >= 97)) {
                        elClass = EL_SIMPLE;
                    } else if (!A.contains("O")) {
                        elClass = EL_SALT;
                    } else if (A.indexOf("O") == A.length() - 1) {
                        elClass = EL_OXIDE;
                    }
                } else {
                    if (!A.contains("O")) {
                        elClass = EL_SALT;
                    } else if (A.indexOf("O") == A.length() - 1) {
                        elClass = EL_OXIDE;
                    } else {
                        elClass = EL_SALT;
                    }
                }
            }
        }
        return elClass;
    }

    public String getIons(String A) { // разделение вещества на ионы
        int elClass = getClass(A);
        String ion1 = "";
        String ion2 = "";
        String ions = "";
        if (elClass == EL_SIMPLE) {
            ion1 += A;
        } else if (elClass == EL_ACID) {
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
        } else if (elClass == EL_ALKALI) {
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
        if (elClass != EL_SIMPLE) {
            ions += ";";
            ions += ion2;
        }
        return ions;
    }

    public String Neitralization(String A, String B) {
        String product1 = "";
        String product2 = "";
        String ionsA = getIons(A);
        String ionsB = getIons(B);
        String[] AIons = ionsA.split(";");
        String[] BIons = ionsB.split(";");
        int valKat = (int) BIons[1].charAt(0) - 48;
        int valAn = (int) AIons[0].charAt(0) - 48;
        Calcval p = new Calcval(valAn,valKat);
        valAn = p.getA();
        valKat = p.getB();
        String AProducts = "";
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

    int TYPE_H_NOTHING = 10;
    int TYPE_H_METALLS_1 = 11;
    int TYPE_H_METALLS_2 = 12;
    int TYPE_H_S = 13;
    int TYPE_H_N = 14;
    int TYPE_H_C = 15;
    int TYPE_H_O = 16;
    int TYPE_H_HAL = 17;

    public int getReagentsH(String A) { //определение более точной классификации вещества
        int typeReagent = TYPE_H_NOTHING;
        if (A.length() == 1) {
            if (A.contains("S")) {
                typeReagent = TYPE_H_S;
            } else {
                if (A.contains("C")) {
                    typeReagent = TYPE_H_C;
                }
            }
        } else {
            if (A.contains("2")) {
                if (A.contains("O")) {
                    typeReagent = TYPE_H_O;
                } else {
                    if (A.contains("F") || A.contains("Cl") || A.contains("Br") || A.contains("I")) {
                        typeReagent = TYPE_H_HAL;
                    } else {
                        if (A.contains("N")) {
                            typeReagent = TYPE_H_N;
                        }
                    }
                }
            } else {
                if (A.contains("Ca") || A.contains("Sr") || A.contains("Ba") || A.contains("Ra")) {
                    typeReagent = TYPE_H_METALLS_2;
                } else {
                    if (A.contains("Na") || A.contains("K") || A.contains("Li") || A.contains("Rb") || A.contains("Cs") || A.contains("Fr")) {
                        typeReagent = TYPE_H_METALLS_1;
                    }
                }
            }
        }
        return typeReagent;
    }


    public String getReactionH(String A) { // реакции для водорода
        String product = "";
        int classA = getClass(A);
        int typeReagent = getReagentsH(A);
        if (classA == 1) {
            switch(typeReagent){
                case 10:
                    product += "реакция не идет";
                break;
                case 11: {
                    product += A;
                    product += "H";
                }
                break;
                case 12: {
                    product += A;
                    product += "H2";
                }
                break;
                case 13:
                    product += "H2S";
                break;
                case 14:
                    product += "NH3";
                break;
                case 15:
                    product += "CH4";
                break;
                case 16:
                    product += "H2O";
                break;
                case 17: {
                    String a1 = "";
                    for (int i = 0; i < (A.length() - 1); i++) {
                        a1 += A.charAt(i);
                    }
                    product += "H";
                    product += a1;
                }
                break;
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

    int TYPE_O_NOTHING = 20;
    int TYPE_O_F = 21;
    int TYPE_O_S = 22;
    int TYPE_O_SI = 23;
    int TYPE_O_P = 24;
    int TYPE_O_N = 25;
    int TYPE_O_C = 26;

    public int getReagentsO(String A) {
        int l = TYPE_O_NOTHING;
        if (A.length() == 1) {
            if (A.contains("S")) {
                l = TYPE_O_S;
            } else {
                if (A.contains("C")) {
                    l = TYPE_O_C;
                } else {
                    if (A.contains("P")) {
                        l = TYPE_O_P;
                    }
                }
            }
        } else {
            if (A.contains("2")) {
                if (A.contains("F")) {
                    l = TYPE_O_F;
                } else {
                    if (A.contains("N")) {
                        l = TYPE_O_N;
                    }
                }
            } else {
                if (A.contains("Si")) {
                    l = TYPE_O_SI;
                } // для написания реакций с металлами необходимо создать таблицу валентностей, иначе будет неверно
            }
        }
        return l;
    }

    public String getReactionO(String A) { // реакции для кислорода
        String product = "";
        int classA = getClass(A);
        if (classA == 1) {
            int TypeReagent = getReagentsO(A);
            switch (TypeReagent) {
                case 20:
                    product += "реакция не идет";
                    break;
                case 21:
                    product += "OF2";
                    break;
                case 22:
                    product += "SO2";
                    break;
                case 23:
                    product += "SiO2";
                    break;
                case 24:
                    product += "P2O5";
                    break;
                case 25:
                    product += "NO2";
                    break;
                case 26:
                    product += "CO2";
                    break;
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

        String kationA = "";
        String anionA = "";
        String kationB = "";
        String anionB = "";
        int kA = 0;
        int aA = 0;
        int kB = 0;
        int aB = 0;
        for (int i = 0; i < KATIONS.length; i++) {
            if (A.contains(KATIONS[i].kation)) {
                kA = i;
            }
            if (B.contains(KATIONS[i].kation)) {
                kB = i;
            }
        }
        for (int i = 0; i < ANIONS.length; i++) {
            if (A.contains(ANIONS[i].anion)) {
                aA = i;
            }
            if (B.contains(ANIONS[i].anion)) {
                aB = i;
            }
        }
        int ap = TABLE[aA][kB];
        int bp = TABLE[aB][kA];
        int r = 0;
        if (ap + bp > 0) {
            r = 1;
        }
        if (r == 1) {
            kationA += KATIONS[kA].kation;
            anionA += ANIONS[aA].anion;
            kationB += KATIONS[kB].kation;
            anionB += ANIONS[aB].anion;
            int valkA = KATIONS[kA].val;
            int valaA = ANIONS[aA].val;
            int valkB = KATIONS[kB].val;
            int valaB = ANIONS[aB].val;
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