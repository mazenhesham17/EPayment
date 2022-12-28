package com.epay.EPayment.Models;

public class Balance {
    final String name;
    double amount;

    public Balance(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void withdraw(double cost) throws Exception {
        if (cost > amount)
            throw new Exception("There is not enough amount in your " + name);
        amount -= cost;
    }

    public void deposit(double amount) {
        this.amount += amount;
    }
}
