package com.epay.EPayment.API;

import com.epay.EPayment.Balance.CreditCard;
import com.epay.EPayment.Controller.CustomerController;
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

    @GetMapping("/customer/show-profile")
    public Response<Customer> showProfile() {
        Response<Customer> response = new Response();
        if (userController.isAdmin()) {
            response.setStatus(false);
            response.setMessage("Can't show admin profile");
            return response;
        }
        response.setStatus(true);
        response.setMessage("");
        response.setObject((Customer) userController.getUser());
        return response;
    }

    @PostMapping("/customer/add-credit-card")
    public Response addCreditCard(@RequestBody CreditCard card) {
        Response response = new Response();
        if (userController.isAdmin()) {
            response.setStatus(false);
            response.setMessage("Can't add Credit Card to admin");
            return response;
        }
        customerController.addCard(card);
        response.setStatus(true);
        response.setMessage(card.getName() + " is added successfully :)");
        return response;
    }

    @GetMapping("/customer/search")
    public Response search(@RequestParam("q") String query) {
        Response response = new Response();
        if (userController.isAdmin()) {
            response.setStatus(false);
            response.setMessage("Admin can't search");
            return response;
        }
        try {
            response.setObject(customerController.searchServices(query));
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return response;
        }
        response.setStatus(true);
        response.setMessage("");
        return response;
    }

    @GetMapping("/customer/show-services")
    public Vector<Container> getServices() {
        ServiceController serviceController = ServiceController.getInstance();
        return serviceController.getServices();
    }
}
