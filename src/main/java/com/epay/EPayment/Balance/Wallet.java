package com.epay.EPayment.Balance;

import com.epay.EPayment.Models.Balance;

public class Wallet extends Balance {

    public Wallet() {
        super(0, "Wallet");
    }

    public void deposit(CreditCard card, double cost, String password) throws Exception {
        card.authenticate(password);
        card.withdraw(cost);
        this.deposit(cost);
    }

}
