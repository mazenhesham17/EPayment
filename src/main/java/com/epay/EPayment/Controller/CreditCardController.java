package com.epay.EPayment.Controller;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.WebView.CreditCardWebView;

import java.util.Vector;

public class CreditCardController {

    static CreditCardController creditCardController = null;
    CreditCard creditCard;

    private CreditCardController() {
    }

    public static CreditCardController getInstance() {
        if (creditCardController == null)
            creditCardController = new CreditCardController();
        return creditCardController;
    }

    public String getName() {
        return creditCard.getName();
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getPassword() {
        return creditCard.getPassword();
    }

    public void withdraw(double cost) throws Exception {
        if (cost > creditCard.getAmount())
            throw new Exception("There is not enough amount in your " + creditCard.getName());
        creditCard.setAmount(creditCard.getAmount() - cost);
    }

    public void deposit(double amount) {
        creditCard.setAmount(creditCard.getAmount() + amount);
    }

    public void authenticate(String password) throws Exception {
        if (creditCard.getPassword().equals(password)) {
            return;
        }
        throw new Exception("The password for " + creditCard.getName() + " is incorrect.");
    }

    public Vector<Container> getCards(Vector<CreditCard> cards) {
        Vector<Container> containers = new Vector<>();
        CreditCardWebView creditCardWebView = CreditCardWebView.getInstance();
        for (CreditCard card : cards) {
            containers.add(creditCardWebView.showCard(card.getName(), card.getAmount(), card.getId()));
        }
        return containers;
    }
}
