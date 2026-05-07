package com.example.adminui;

public class TutorModel {
    private String name;
    private int studentCount;
    private float rating;

    public TutorModel() {
    }

    public TutorModel(String name, int studentCount, float rating) {
        this.name = name;
        this.studentCount = studentCount;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
