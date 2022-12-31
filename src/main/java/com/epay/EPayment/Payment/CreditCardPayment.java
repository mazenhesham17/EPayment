package com.epay.EPayment.Payment;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Controller.CreditCardController;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(PaymentDetails paymentDetails) {
        super(paymentDetails);
        paymentDetails.setDetail("name", "Credit Card");
    }

    public void authenticate(String password) throws Exception {
        CreditCardController creditCardController = CreditCardController.getInstance();
        creditCardController.setCreditCard((CreditCard) balance);
        creditCardController.authenticate(password);
    }

    String getPassword() {
        return paymentDetails.getDetail("password");
    }

    @Override
    public boolean pay() throws Exception {
        String password = getPassword();
        authenticate(password);
        return super.pay();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        CreditCardController creditCardController = CreditCardController.getInstance();
        creditCardController.setCreditCard((CreditCard) balance);
        creditCardController.withdraw(amount);
    }

    @Override
    public Payment clone(int index) {
        CreditCardPayment creditCardPayment = new CreditCardPayment(new PaymentDetails());
        creditCardPayment.setId(this.getId());
        return creditCardPayment;
    }

}
