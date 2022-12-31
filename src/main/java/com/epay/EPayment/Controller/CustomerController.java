package com.epay.EPayment.Controller;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Balance.Wallet;
import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Discount.DiscountData;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Search.ConcreteSearch;
import com.epay.EPayment.Search.Search;
import com.epay.EPayment.Service.Service;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import com.epay.EPayment.Transaction.RefundTransaction;
import com.epay.EPayment.Transaction.Transaction;
import com.epay.EPayment.Util.Container;

import java.util.Vector;

public class CustomerController {

    static CustomerController customerController = null;
    Customer customer;

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        if (customerController == null)
            customerController = new CustomerController();
        return customerController;
    }

    public void addCard(CreditCard card) {
        int id = customer.getCards().size() + 1;
        card.setId(id);
        customer.getCards().add(card);
    }

    public Vector<Container> searchServices(String sub) throws Exception {
        ServiceController serviceController = ServiceController.getInstance();
        Search search = new ConcreteSearch();
        return serviceController.getWebView(search.find(sub));
    }

    public Service chooseService(Vector<Service> services, int index) {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.chooseService(services, index);
    }

    public CreditCard chooseCard(int id) throws Exception {
        if (customer.getCards().isEmpty())
            throw new Exception("You don't have any cards :( ");
        if (id < 1 || id > customer.getCards().size())
            throw new Exception("Card id not in the range from 1 to " + customer.getCards().size());
        return customer.getCards().get(id - 1);
    }

    public void chargeWallet(CreditCard card, double cost, String password) throws Exception {
        WalletController walletController = WalletController.getInstance();
        walletController.setWallet(customer.getWallet());
        walletController.deposit(card, cost, password);
    }

    public Wallet getWallet() {
        return customer.getWallet();
    }

    public DiscountData getDiscountData() {
        return customer.getDiscountData();
    }

    public Vector<Container> getAllDiscounts() throws Exception {
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(customer.getDiscountData());
        return discountController.getWebDiscounts();
    }

    public void addTransaction(Transaction transaction) {
        customer.getTransactions().add(transaction);
    }

    public void addRefund(Transaction transaction) {
        TransactionController transactionController = TransactionController.getInstance();
        transactionController.setTransaction(transaction);
        transactionController.setRequested();
        Refund refund = new Refund(transaction);
        customer.getRefunds().add(refund);
        notifyAdmins(refund);
    }

    public void refund(Refund refund) throws Exception {
        Transaction transaction = refund.getTransaction();
        TransactionController transactionController = TransactionController.getInstance();
        if (transaction instanceof PaymentTransaction) {
            WalletController walletController = WalletController.getInstance();
            walletController.setWallet(customer.getWallet());
            walletController.deposit(transactionController.getAmount());
        } else if (transaction instanceof ChargeTransaction) {
            CreditCardController creditCardController = CreditCardController.getInstance();
            creditCardController.setCreditCard(((ChargeTransaction) transaction).getCreditCard());
            creditCardController.deposit(transactionController.getAmount());
        } else {
            throw new Exception("You can not make refund in Refunded requests ");
        }
        Transaction refundTransaction = new RefundTransaction(transactionController.getCustomer(), transactionController.getAmount(), refund);
        addTransaction(refundTransaction);
    }

    private void notifyAdmins(Refund refund) {
        UserData userData = UserData.getInstance();
        Vector<User> users = userData.getUsers();
        for (User user : users) {
            if (user instanceof Admin) {
                AdminController adminController = AdminController.getInstance();
                adminController.setAdmin((Admin) user);
                adminController.update(refund);
            }
        }
    }

    public Vector<Container> getRefunds() throws Exception {
        if (customer.getRefunds().isEmpty())
            throw new Exception("There is no refund requests :)");
        RefundController refundController = RefundController.getInstance();
        customer.clear();
        return refundController.getRefunds(customer.getRefunds());
    }

    public void update() {
        customer.increment();
    }

    public Vector<Container> getCards() throws Exception {
        if (customer.getCards().isEmpty())
            throw new Exception("There is no cards :(");
        CreditCardController creditCardController = CreditCardController.getInstance();
        return creditCardController.getCards(customer.getCards());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vector<Transaction> getTransactions() {
        return customer.getTransactions();
    }

    public String getUserName() {
        return customer.getUsername();
    }
}
