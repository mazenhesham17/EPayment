package com.epay.EPayment.View;

import com.epay.EPayment.Balance.Wallet;

public class WalletView {

    static WalletView walletView = null;
    Wallet wallet;

    private WalletView() {
    }

    public static WalletView getInstance() {
        if (walletView == null)
            walletView = new WalletView();
        return walletView;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    void showWalletAmount() {
        System.out.println("The wallet has " + wallet.getAmount() + " amount.");
    }
}
