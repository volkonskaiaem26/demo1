package com.example.demo1;

public class Vaal {
    public int valmeo(String A){ // определение валентностей металлов в оскидах(возможо это для чего то нужно...)
        int n = A.indexOf("O");
        int v =1;
        if((int) A.charAt(n-1) >=48 && (int) A.charAt(n-1)<=57){
            v = (int) A.charAt(n-1) -48;
            v = v/2;
            if((int) A.charAt(A.length()-1) >=48 && (int) A.charAt(A.length()-1)<=57){
                int o = (int) A.charAt(A.length()-1) - 48;
                v *= o;
            }
        }else{
            v = 2;
            if((int) A.charAt(A.length()-1) >=48 && (int) A.charAt(A.length()-1)<=57){
                int o = (int) A.charAt(A.length()-1) - 48;
                v *= o;
            }
        }
        return v;
    }
}
