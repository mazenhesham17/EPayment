package com.epay.EPayment.API;

import com.epay.EPayment.Controller.*;
import com.epay.EPayment.DataSet.UserData;
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
    ResponseController responseController = ResponseController.getInstance() ;

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody Customer customer) {
        Response response = new Response<>();
        responseController.setResponse(response);
        try {
            userController.signUp(customer);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        responseController.setSuccess("User Registered Successfully :)");
        response.setStatus(true);
        return response;
    }

    @PostMapping("/sign-in")
    public Response signIn(@RequestBody User user) {
        Response response = new Response<>();
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
        return response;
    }

    @PutMapping("/sign-out")
    public Response signUp() {
        Response response = new Response<>();
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
