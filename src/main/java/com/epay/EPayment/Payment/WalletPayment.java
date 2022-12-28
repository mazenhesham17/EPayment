package com.epay.EPayment.Payment;

import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.PaymentDetails;

public class WalletPayment extends Payment {
    public WalletPayment(PaymentDetails paymentDetails) {
        super(paymentDetails);
        paymentDetails.setDetail("name", "Wallet");
    }

    @Override
    public Payment clone(int index) {
        return new WalletPayment(new PaymentDetails());
    }
}
