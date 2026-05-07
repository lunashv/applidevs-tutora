package com.example.adminui;

public class Student {
    private String name;
    private int gradeLevel;
    private String paymentStatus;

    public Student(String name, int gradeLevel, String paymentStatus) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.paymentStatus = paymentStatus;
    }

    public String getName() { return name; }
    public int getGradeLevel() { return gradeLevel; }
    public String getPaymentStatus() { return paymentStatus; }
}
