package com.example.abhishektiwari.gpacalculator;

public class UserGrades {
    public double sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;

    public UserGrades(){

    }
    public UserGrades(double sem1,double sem2,double sem3,double sem4,double sem5,double sem6,double sem7,double sem8){
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.sem5 = sem5;
        this.sem6 = sem6;
        this.sem7 = sem7;
        this.sem8 = sem8;
    }

    public String getSem1() {
        return String.valueOf(sem1);
    }

    public void setSem1(double sem1) {
        this.sem1 = sem1;
    }

    public String getSem2() {
        return String.valueOf(sem2);
    }

    public void setSem2(double sem2) {
        this.sem2 = sem2;
    }

    public String getSem3() {
        return String.valueOf(sem3);
    }

    public void setSem3(double sem3) {
        this.sem3 = sem3;
    }

    public String getSem4() {
        return String.valueOf(sem4);
    }

    public void setSem4(double sem4) {
        this.sem4 = sem4;
    }

    public String getSem5() {
        return String.valueOf(sem5);
    }

    public void setSem5(double sem5) {
        this.sem5 = sem5;
    }

    public String getSem6() {
        return String.valueOf(sem6);
    }

    public void setSem6(double sem6) {
        this.sem6 = sem6;
    }

    public String getSem7() {
        return String.valueOf(sem7);
    }

    public void setSem7(double sem7) {
        this.sem7 = sem7;
    }

    public String getSem8() {
        return String.valueOf(sem8);
    }

    public void setSem8(double sem8) {
        this.sem8 = sem8;
    }
}
