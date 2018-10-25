package com.example.abhishektiwari.gpacalculator;

public class UserGrades {
    public int sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;

    public UserGrades(){

    }
    public UserGrades(int sem1,int sem2,int sem3,int sem4,int sem5,int sem6,int sem7,int sem8){
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

    public void setSem1(int sem1) {
        this.sem1 = sem1;
    }

    public String getSem2() {
        return String.valueOf(sem2);
    }

    public void setSem2(int sem2) {
        this.sem2 = sem2;
    }

    public String getSem3() {
        return String.valueOf(sem3);
    }

    public void setSem3(int sem3) {
        this.sem3 = sem3;
    }

    public String getSem4() {
        return String.valueOf(sem4);
    }

    public void setSem4(int sem4) {
        this.sem4 = sem4;
    }

    public String getSem5() {
        return String.valueOf(sem5);
    }

    public void setSem5(int sem5) {
        this.sem5 = sem5;
    }

    public String getSem6() {
        return String.valueOf(sem6);
    }

    public void setSem6(int sem6) {
        this.sem6 = sem6;
    }

    public String getSem7() {
        return String.valueOf(sem7);
    }

    public void setSem7(int sem7) {
        this.sem7 = sem7;
    }

    public String getSem8() {
        return String.valueOf(sem8);
    }

    public void setSem8(int sem8) {
        this.sem8 = sem8;
    }
}
