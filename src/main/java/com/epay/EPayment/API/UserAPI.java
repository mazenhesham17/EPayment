package com.epay.EPayment.API;

import com.epay.EPayment.Controller.AdminController;
import com.epay.EPayment.Controller.CustomerController;
import com.epay.EPayment.Controller.UserController;
import com.epay.EPayment.Models.*;
import com.epay.EPayment.Search.ConcreteSearch;
import com.epay.EPayment.Search.Search;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
public class UserAPI {

    UserController userController = UserController.getInstance() ;
    @PostMapping("/sign-up")
    public Response signUp(@RequestBody Customer customer){
        Response response = new Response<>() ;
        try {
            userController.signUp(customer);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return response ;
        }
        response.setStatus(true);
        response.setMessage("User Registered Successfully :)");
        return response ;
    }

    @PostMapping("/sign-in")
    public Response signIn(@RequestBody User user){
        Response response = new Response<>() ;
        try {
            userController.signIn(user);
            if ( userController.isAdmin() ){
                AdminController adminController = AdminController.getInstance() ;
                adminController.setAdmin((Admin) userController.getUser());
            }else{
                CustomerController customerController = CustomerController.getInstance() ;
                customerController.setCustomer((Customer) userController.getUser());
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return response ;
        }
        response.setStatus(true);
        response.setMessage("Welcome " + userController.getUsername() + " :)");
        return response ;
    }

    @PutMapping("/sign-out")
    public Response signUp(){
        Response response = new Response<>() ;
        try {
            userController.signOut();
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return response ;
        }
        response.setStatus(true);
        response.setMessage("User Signed out successfully :(");
        return response ;
    }
    @GetMapping("/service")
    public Vector<Service> getService(){
        Search search = new ConcreteSearch() ;
        return search.listAll() ;
    }

}
