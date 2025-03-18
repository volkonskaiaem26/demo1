package com.example.demo1;

public class Calcval {
    int A;
    int B;

    Calcval(int a, int b){
        if(a%b==0){
            b = 1;
            a = a/b;
        }else{
            if(b%a==0){
                a =1;
                b = b/a;
            }
        }
        this.A = a;
        this.B = b;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }
}
