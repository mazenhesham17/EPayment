package com.epay.EPayment.API;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Controller.*;
import com.epay.EPayment.Models.*;
import com.epay.EPayment.Transaction.ChargeTransaction;
import com.epay.EPayment.Transaction.PaymentTransaction;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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

    @GetMapping("/customer/show-wallet")
    public Response showWallet() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        responseController.setSuccess(customerController.getWallet());
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

    @GetMapping("/customer/show-service")
    public Response getService(@RequestParam("id") int id) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        ServiceController serviceController = ServiceController.getInstance();
        try {
            responseController.setSuccess(serviceController.getService(id));
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
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

    @PostMapping("/customer/use-service")
    public Response useService(@RequestBody Map<String, String> map) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        ServiceController serviceController = ServiceController.getInstance();
        // choose service
        int serviceId = Integer.parseInt(map.get("serviceId"));
        Service service;
        try {
            service = serviceController.chooseService(serviceId);
            serviceController.setService(service);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        // take form data
        HashMap<String, String[]> fields = serviceController.getFormFields();
        for (Map.Entry<String, String[]> entry : fields.entrySet()) {
            String name = entry.getKey();
            if (!map.containsKey(name)) {
                responseController.setFailure(name + " Field is not available");
                return response;
            }
            String[] items = entry.getValue();
            String value;
            if (items.length == 0) {
                value = map.get(name);
            } else {
                int itemChoice = Integer.parseInt(map.get(name));
                if (itemChoice < 1 || itemChoice > items.length) {
                    responseController.setFailure("Invalid selection for " + name + " Field");
                    return response;
                }
                value = items[itemChoice - 1];
            }
            serviceController.setFormDataField(name, value);
        }
        // use discount if there is
        double before = serviceController.getCost();
        DiscountController discountController = DiscountController.getInstance();
        discountController.setDiscountData(customerController.getDiscountData());
        Vector<Discount> discounts = discountController.useDiscounts(service);
        double after = discountController.applyDiscounts(before, discounts);
        // choose payment method
        int paymentId = Integer.parseInt(map.get("paymentId"));
        Payment payment;
        try {
            payment = serviceController.choosePayment(paymentId);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        PaymentController paymentController = PaymentController.getInstance();
        paymentController.setPayment(payment);
        if (paymentId == 1) {
            int cardId = Integer.parseInt(map.get("cardId"));
            CreditCard creditCard;
            try {
                creditCard = customerController.getCard(cardId);
                paymentController.setBalance(creditCard);
            } catch (Exception e) {
                responseController.setFailure(e.getMessage());
                return response;
            }
            String password = map.get("password");
            paymentController.setPassword(password);
        } else if (paymentId == 2) {
            paymentController.setBalance(customerController.getWallet());
        }
        paymentController.setCost(after);
        try {
            paymentController.pay();
            responseController.setSuccess("Successful Payment :)", discountController.getWebDiscounts(discounts));
            Transaction transaction = new PaymentTransaction(customerController.getCustomer(), service, before, after);
            customerController.addTransaction(transaction);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            discountController.returnDiscounts(discounts);
            return response;
        }
        return response;
    }

    @GetMapping("/customer/show-refunds")
    public Response getRefunds() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        try {
            responseController.setSuccess(customerController.getRefunds());
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        return response;
    }

    @PostMapping("/customer/apply-refund")
    public Response applyRefund(@RequestParam("id") int id) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        Transaction transaction;
        TransactionController transactionController = TransactionController.getInstance();
        try {
            transaction = transactionController.chooseTransaction(id);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        customerController.addRefund(transaction);
        responseController.setSuccess("Your request has been submitted successfully");
        return response;
    }

    @GetMapping("/customer/check-notifications")
    public Response checkNotifications() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        if (userController.checkNotifications())
            responseController.setSuccess(userController.showNotifications());
        else
            responseController.setFailure("There is no new updates");
        return response;
    }
}
