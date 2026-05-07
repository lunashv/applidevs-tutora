package com.example.adminui;
public class Transaction {
    private String name;
    private String datePaid;
    private String paymentStatus;

    public Transaction(String name, String datePaid, String paymentStatus) {
        this.name = name;
        this.datePaid = datePaid;
        this.paymentStatus = paymentStatus;
    }

    public String getName() { return name; }
    public String getDatePaid() { return datePaid; }
    public String getPaymentStatus() { return paymentStatus; }
}
