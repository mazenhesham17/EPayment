package com.epay.EPayment.Balance;

import com.epay.EPayment.Models.Balance;

public class CreditCard extends Balance {
    String number;
    String password;

    public CreditCard(String name, String number, String password) {
        super(2500, name);
        this.number = number;
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void authenticate(String password) throws Exception {
        if (this.password.equals(password)) {
            return;
        }
        throw new Exception("The password for " + this.getName() + " is incorrect.");
    }

}
