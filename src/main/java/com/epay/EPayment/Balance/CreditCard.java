package com.epay.EPayment.Balance;

public class CreditCard extends Balance {
    String number;
    String password;
    int id;

    public CreditCard(String name, String number, String password) {
        super(2500, name);
        this.number = number;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}
