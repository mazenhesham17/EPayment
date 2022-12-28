package com.epay.EPayment.Transaction;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Transaction;

public class ChargeTransaction extends Transaction {
    CreditCard creditCard;

    public ChargeTransaction(Customer customer, double amount, CreditCard card) {
        super(customer, amount);
        super.setTransactionType("Charge Transaction");
        super.setPaymentMethod("Credit Card");
        creditCard = card;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

}
