package com.epay.EPayment.Balance;

public class Balance {
    String name;
    double amount;

    public Balance(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

}
