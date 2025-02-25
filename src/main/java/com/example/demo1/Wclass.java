package com.example.demo1;

public class Wclass {

    String A;

    Wclass(String A){
        this.A =  A;
    }

    public int WC(String a){
        int l = 0;
        if(a.contains("OH")){
            l = 3;
        }else{
            if(a.contains("H")){
                if(a.length() == 2 && a.contains("2")){
                    l = 1;
                }else{
                    if(a.indexOf("H")==1){
                        l = 2;
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
}
