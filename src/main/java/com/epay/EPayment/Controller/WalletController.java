package com.epay.EPayment.Controller;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Balance.Wallet;
import com.epay.EPayment.Util.Container;

public class WalletController {

    static WalletController walletController = null;
    Wallet wallet;

    private WalletController() {
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public static WalletController getInstance() {
        if (walletController == null)
            walletController = new WalletController();
        return walletController;
    }

    public void deposit(CreditCard card, double cost, String password) throws Exception {
        CreditCardController creditCardController = CreditCardController.getInstance() ;
        creditCardController.setCreditCard(card);
        creditCardController.authenticate(password);
        creditCardController.withdraw(cost);
        deposit(cost);
    }

    public void withdraw(double cost) throws Exception {
        if (cost > wallet.getAmount())
            throw new Exception("There is not enough amount in your " + wallet.getName());
        wallet.setAmount(wallet.getAmount()-cost);
    }

    public void deposit(double amount) {
        wallet.setAmount(wallet.getAmount()+amount);
    }
}
