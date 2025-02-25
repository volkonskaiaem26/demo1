package com.example.demo1;

public class Main {

    String A;
    String B;
    int Aclass;
    int Bclass;

    public String getIons(String A, int a){
        String ion1 = "";
        String ion2 = "";
        String ions = "";
        int k = 0;
        if(a==1){
            ion1 += A;
            k = 1;
        }else{
            if(a==2){
                int h = (int) A.charAt(1) - 48;
                ion1 += h +"H";
                for(int i=2;i<A.length();i++){
                    ion2 += A.charAt(i);
                }
        }else{
            if(a==3){
                int h1 = (int) A.charAt(A.length()-1) - 48;
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
        if(k==1){
            ions += ";";
            ions += ion2;
        }
        return ions;
    }
    public String Reaction(String A, String B, int k, int c) {
        String product1 = "";
        String product2 = "";
        String ionsA = String.valueOf(getIons(A, k));
        String ionsB = String.valueOf(getIons(B, c));
        int r = 0;
        if (k == 2 && c == 3) {
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
            product1 += Bp + Ap;
            product2 += "H2O";
        } else {
            if (k == 3 && c == 2) {
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
            } if (k == 1) {
                if (A.contains("H2")) {
                    int l = Reag(B);
                    String cs = React(B, l);
                    product1 += cs;
                    r = 1;
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
        public int Reag (String A){
            int l = 0;
            if (A.length() == 1) {
                if (A.contains("S")) {
                    l = 3;
                } else {
                    if (A.contains("N")) {
                        l = 4;
                    } else {
                        if (A.contains("C")) {
                            l = 5;
                        }
                    }
                }
            } else {
                if (A.contains("2")) {
                    if (A.contains("O")) {
                        l = 6;
                    } else {
                        if (A.contains("F") && A.contains("Cl") && A.contains("Br") && A.contains("I")) {
                            l = 7;
                        }
                    }
                } else {
                    if (A.contains("Ca") && A.contains("Sr") && A.contains("Ba") && A.contains("Ra")) {
                        l = 2;
                    } else {
                        if (A.contains("Na") && A.contains("K") && A.contains("Li") && A.contains("Rb") && A.contains("Cs") && A.contains("Fr")) {
                            l = 1;
                        }
                    }
                }
            }
            return l;
        }


    public String React(String A,int k){
        String product = "";
        if(k==1){
            int l = Reag(A);
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
}