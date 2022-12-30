package com.epay.EPayment.API;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Controller.CustomerController;
import com.epay.EPayment.Controller.ResponseController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Response;
import com.epay.EPayment.Util.Container;
import org.springframework.web.bind.annotation.*;

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
            return response ;
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
    public Vector<Container> getServices() {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.getServices();
    }
}
