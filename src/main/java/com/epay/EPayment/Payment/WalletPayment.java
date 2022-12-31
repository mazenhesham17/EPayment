package com.epay.EPayment.Payment;

import com.epay.EPayment.Balance.Wallet;
import com.epay.EPayment.Controller.WalletController;

public class WalletPayment extends Payment {
    public WalletPayment(PaymentDetails paymentDetails) {
        super(paymentDetails);
        paymentDetails.setDetail("name", "Wallet");
    }

    @Override
    public void withdraw(double amount) throws Exception {
        WalletController walletController = WalletController.getInstance();
        walletController.setWallet((Wallet) balance);
        walletController.withdraw(amount);
    }

    @Override
    public Payment clone(int dummy) {
        WalletPayment walletPayment = new WalletPayment(new PaymentDetails());
        walletPayment.setId(this.getId());
        return walletPayment;
    }
}
