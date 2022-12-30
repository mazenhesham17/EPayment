package com.epay.EPayment.API;

import com.epay.EPayment.Controller.AdminController;
import com.epay.EPayment.Controller.DiscountController;
import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.DataSet.UserData;
import com.epay.EPayment.Discount.OverallDiscount;
import com.epay.EPayment.Discount.SpecificDiscount;
import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.Response;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Models.User;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.View.CategoryWebView;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class AdminAPI {
    @GetMapping("/category")
    public Response getCategory (@RequestParam("id") int id){
        ServiceController serviceController = ServiceController.getInstance() ;
        Response response = new Response() ;
        try {
            response.setObject(serviceController.getCategory(id));
            response.setStatus(true);
            response.setMessage("");
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return response ;
        }
        return response ;
    }
    @GetMapping("/users")
    public Vector<User> getUsers() {
        UserData userData = UserData.getInstance();
        return userData.getUsers();
    }
    @GetMapping("/show-categories")
    public Vector<Container>getCategories(){
        ServiceController serviceController = ServiceController.getInstance() ;
        return serviceController.getCategories() ;
    }
    @PostMapping("/add-specific-discount")
    public Response addSpecificDiscount(@RequestBody Map<String,String>map){
        Response response = new Response() ;
        DiscountController discountController = DiscountController.getInstance() ;
        try {
            String name = map.get("name") ;
            int percentage = Integer.parseInt(map.get("percentage"));
            int id = Integer.parseInt(map.get("id"));
            AdminController adminController = AdminController.getInstance() ;
            SpecificDiscount discount = discountController.getSpecificDiscount(name , percentage , id ) ;
            adminController.addSpecificDiscount(discount , id);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return  response ;
        }
        response.setStatus(true);
        response.setMessage("Discount has been added Successfully");
        return response ;
    }
    @PostMapping("/add-overall-discount")
    public Response addOverallDiscount(@RequestBody OverallDiscount discount){
        Response response = new Response() ;
        AdminController adminController = AdminController.getInstance() ;
        adminController.addOverallDiscount(discount);
        response.setStatus(true);
        response.setMessage("Discount has been added Successfully");
        return response ;
    }
}
