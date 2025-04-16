package com.example.demo1;

public class Calcval {
    int A;
    int B;

    Calcval(int a, int b){
        if(a%b==0){
            a = a/b;
            b = 1;
        }else{
            if(b%a==0){
                b = b/a;
                a = 1;
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
