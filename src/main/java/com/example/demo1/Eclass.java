package com.example.demo1;

public class Eclass {
    String A;
    String B;

    Eclass(String text){
        String[] s1 = text.split("+");
        this.A=s1[0];
        this.B=s1[1];
    }

    public String getA() {
        return A;
    }

    public String getB() {
        return B;
    }
}
