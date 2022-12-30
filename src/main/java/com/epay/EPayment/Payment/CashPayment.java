package com.epay.EPayment.Payment;

import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.PaymentDetails;

public class CashPayment extends Payment {
    public CashPayment(PaymentDetails paymentDetails) {
        super(paymentDetails);
        paymentDetails.setDetail("name", "Cash on delivery");
    }


    @Override
    public void withdraw(double amount) throws Exception {
        System.out.println("You will pay " + amount + " in delivery");
    }

    @Override
    public Payment clone(int index) {
        CashPayment cashPayment = new CashPayment(new PaymentDetails());
        cashPayment.setId(this.getId());
        return cashPayment;
    }
}
