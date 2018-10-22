package com.example.abhishektiwari.gpacalculator;

public class GradeList {

    public String ex,a,b,c,d,p;

    public GradeList(){

    }
    public GradeList(String ex, String a, String b,String c,String d,String p){
        this.ex = ex;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.p = p;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
