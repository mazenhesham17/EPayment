package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Transaction;

public class TransactionController {
    static TransactionController transactionController = null;
    Transaction transaction;

    private TransactionController() {

    }

    public static TransactionController getInstance() {
        if (transactionController == null)
            transactionController = new TransactionController();
        return transactionController;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


}
