package com.epay.EPayment.Models;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Balance.Wallet;
import com.epay.EPayment.Controller.DiscountController;
import com.epay.EPayment.Service.DonationsService;
import com.epay.EPayment.Service.InternetPaymentService;
import com.epay.EPayment.Service.LandlineService;
import com.epay.EPayment.Service.MobileRechargeService;

import java.util.Vector;

public class Customer extends User {
    Wallet wallet;
    Vector<CreditCard> cards;
    Vector<Transaction> transactions;
    DiscountData discountData;

    public Customer(String email, String username, String password) {
        super(email, username, password);
        wallet = new Wallet();
        cards = new Vector<>();
        transactions = new Vector<>();
        discountData = new DiscountData();
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(discountData);
        discountController.addCategory(new MobileRechargeService());
        discountController.addCategory(new InternetPaymentService());
        discountController.addCategory(new LandlineService());
        discountController.addCategory(new DonationsService());
    }

    public DiscountData getDiscountData() {
        return discountData;
    }

    public Vector<CreditCard> getCards() {
        return cards;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Vector<Transaction> getTransactions() {
        return transactions;
    }
}
