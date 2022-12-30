package com.epay.EPayment.Controller;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Util.Container;

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

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getPassword(){
        return creditCard.getPassword();
    }

    public void withdraw(double cost) throws Exception {
        if (cost > creditCard.getAmount())
            throw new Exception("There is not enough amount in your " + creditCard.getName());
        creditCard.setAmount(creditCard.getAmount()-cost);
    }

    public void deposit(double amount) {
        creditCard.setAmount(creditCard.getAmount()+amount);
    }

    public void authenticate(String password) throws Exception {
        if (creditCard.getPassword().equals(password)) {
            return;
        }
        throw new Exception("The password for " + creditCard.getName() + " is incorrect.");
    }
}
