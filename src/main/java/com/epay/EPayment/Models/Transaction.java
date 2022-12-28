package com.epay.EPayment.Models;

import com.epay.EPayment.Time.TimeManager;

import java.util.Date;

public abstract class Transaction {
    static int serial = 0;
    String paymentMethod;
    String transactionType;
    Customer customer;
    Date date;
    double amount;
    int id;

    boolean requested;

    public Transaction(Customer customer, double amount) {
        id = ++serial;
        date = TimeManager.getTime();
        this.customer = customer;
        this.amount = amount;
        this.requested = false;
    }

    public String getTransactionType() {
        return transactionType;
    }

    protected void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }
}
