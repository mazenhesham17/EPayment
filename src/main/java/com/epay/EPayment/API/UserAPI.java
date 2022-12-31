package com.epay.EPayment.API;

import com.epay.EPayment.Controller.AdminController;
import com.epay.EPayment.Controller.CustomerController;
import com.epay.EPayment.Controller.ResponseController;
import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Customer;
import com.epay.EPayment.Models.Response;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Util.Container;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
public class UserAPI {

    UserController userController = UserController.getInstance();
    ResponseController responseController = ResponseController.getInstance();

    @PostMapping("/sign-up")
    public Response<String> signUp(@RequestBody Customer customer) {
        Response<String> response = new Response<>();
        responseController.setResponse(response);
        try {
            userController.signUp(customer);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        responseController.setSuccess("User Registered Successfully :)");
        return response;
    }

    @PostMapping("/sign-in")
    public Response<String> signIn(@RequestBody User user) {
        Response<String> response = new Response<>();
        responseController.setResponse(response);
        try {
            userController.signIn(user);
            if (userController.isAdmin()) {
                AdminController adminController = AdminController.getInstance();
                adminController.setAdmin((Admin) userController.getUser());
            } else {
                CustomerController customerController = CustomerController.getInstance();
                customerController.setCustomer((Customer) userController.getUser());
            }
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        responseController.setSuccess("Welcome " + userController.getUsername() + " :)");
        if (userController.checkNotifications())
            responseController.setObject(userController.showNotifications());
        return response;
    }

    @PutMapping("/sign-out")
    public Response<String> signOut() {
        Response<String> response = new Response<>();
        responseController.setResponse(response);
        try {
            userController.signOut();
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        responseController.setSuccess("User Signed out successfully :(");
        return response;
    }

    @GetMapping("/users")
    public Vector<Container> getUsers() {
        return userController.getUsers();
    }

}
