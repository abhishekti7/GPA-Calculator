package com.example.abhishektiwari.gpacalculator;

public class Profile {

    public UserGrades userGrades;
    public UserProfile userProfile;
    public GradeList gradeList;

    public Profile(){

    }

    public Profile(UserProfile userProfile, UserGrades userGrades,GradeList gradeList){
        this.userProfile = userProfile;
        this.userGrades = userGrades;
        this.gradeList = gradeList;
    }

    public GradeList getGradeList() {
        return gradeList;
    }

    public void setGradeList(GradeList gradeList) {
        this.gradeList = gradeList;
    }

    public UserGrades getUserGrades() {
        return userGrades;
    }

    public void setUserGrades(UserGrades userGrades) {
        this.userGrades = userGrades;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
