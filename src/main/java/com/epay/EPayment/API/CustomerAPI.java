package com.epay.EPayment.API;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Controller.*;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Response;
import com.epay.EPayment.Models.Transaction;
import com.epay.EPayment.Transaction.ChargeTransaction;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CustomerAPI {
    UserController userController = UserController.getInstance();
    CustomerController customerController = CustomerController.getInstance();

    ResponseController responseController = ResponseController.getInstance();

    boolean isValid() {
        if (!userController.isSigned()) {
            responseController.setFailure("You are not signed in !!");
            return false;
        }
        if (userController.isAdmin()) {
            responseController.setFailure("Admin is not allowed to access this page :(");
            return false;
        }
        return true;
    }

    @GetMapping("/customer/show-profile")
    public Response<Customer> showProfile() {
        Response<Customer> response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        responseController.setSuccess((Customer) userController.getUser());
        return response;
    }

    @GetMapping("/customer/show-discounts")
    public Response showDiscounts() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;

        try {
            responseController.setSuccess(customerController.showAllDiscounts());
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        return response;
    }

    @PostMapping("/customer/add-credit-card")
    public Response addCreditCard(@RequestBody CreditCard card) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        customerController.addCard(card);
        responseController.setSuccess(card.getName() + " is added successfully :)");
        return response;
    }

    @GetMapping("/customer/search")
    public Response search(@RequestParam("q") String query) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;

        try {
            responseController.setSuccess(customerController.searchServices(query));
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        return response;
    }

    @GetMapping("/customer/show-services")
    public Response getServices() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        ServiceController serviceController = ServiceController.getInstance();
        responseController.setSuccess(serviceController.getServices());
        return response;
    }

    @GetMapping("/customer/show-payments")
    public Response getPayments() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        PaymentController paymentController = PaymentController.getInstance();
        responseController.setSuccess(paymentController.getPayments());
        return response;

    }

    @GetMapping("/customer/show-cards")
    public Response getCards() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        try {
            responseController.setSuccess(customerController.getCards());
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
        }
        return response;
    }

    @PutMapping("/customer/charge-wallet")
    public Response chargeWallet(@RequestBody Map<String, String> map) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        double amount = Double.parseDouble(map.get("amount"));
        int id = Integer.parseInt(map.get("cardId"));
        String password = map.get("password");
        CreditCard card;
        try {
            card = customerController.getCard(id);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        try {
            customerController.chargeWallet(
                    card,
                    amount,
                    password);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        Transaction transaction = new ChargeTransaction(customerController.getCustomer(), amount, card);
        customerController.addTransaction(transaction);
        responseController.setSuccess(amount + " added to your wallet successfully :)");
        return response;
    }

    @GetMapping("/customer/show-transactions")
    public Response getTransactions() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        TransactionController transactionController = TransactionController.getInstance();
        try {
            responseController.setSuccess(transactionController.getTransactions());
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        return response;
    }
}
