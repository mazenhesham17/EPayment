package com.epay.EPayment.Payment;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.PaymentDetails;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(PaymentDetails paymentDetails) {
        super(paymentDetails);
        paymentDetails.setDetail("name", "Credit Card");
    }

    public void authenticate(String password) throws Exception {
        ((CreditCard) balance).authenticate(password);
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
    public Payment clone(int index) {
        return new CreditCardPayment(new PaymentDetails());
    }

}
