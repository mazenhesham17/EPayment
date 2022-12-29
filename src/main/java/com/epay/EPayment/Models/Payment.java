package com.epay.EPayment.Models;

abstract public class Payment {
    protected Balance balance;
    protected PaymentDetails paymentDetails;

    public Payment(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        paymentDetails.setDetail("password", password);
    }

    public boolean pay() throws Exception {
        double amount = getCost();
        withdraw(amount);
        return true;
    }

    double getCost() {
        return Double.parseDouble(paymentDetails.getDetail("cost"));
    }

    public void setCost(double cost) {
        paymentDetails.setDetail("cost", Double.toString(cost));
    }

    public String getName() {
        return paymentDetails.getDetail("name");
    }


    public void withdraw(double amount) throws Exception {
        balance.withdraw(amount);
    }

    public abstract Payment clone(int index);
}