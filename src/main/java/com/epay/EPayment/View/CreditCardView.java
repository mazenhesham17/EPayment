package com.epay.EPayment.View;

import com.epay.EPayment.Balance.CreditCard;

public class CreditCardView {

    static CreditCardView creditCardView = null;
    CreditCard card;

    private CreditCardView() {
    }

    public static CreditCardView getInstance() {
        if (creditCardView == null)
            creditCardView = new CreditCardView();
        return creditCardView;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    void showCardName(int index) {
        System.out.println(index + ". " + card.getName());
    }

    void showCardDetails() {
        System.out.println("Name : " + card.getName() + "\t" + "Amount : " + card.getAmount());
        System.out.println("Serial Number : " + card.getNumber());
    }

}
