package com.epay.EPayment.API;

import com.epay.EPayment.Controller.*;
import com.epay.EPayment.Discount.OverallDiscount;
import com.epay.EPayment.Discount.SpecificDiscount;
import com.epay.EPayment.Models.Response;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AdminAPI {
    UserController userController = UserController.getInstance();
    AdminController adminController = AdminController.getInstance();
    ResponseController responseController = ResponseController.getInstance();

    boolean isValid() {
        if (!userController.isSigned()) {
            responseController.setFailure("You are not signed in !!");
            return false;
        }
        if (userController.isCustomer()) {
            responseController.setFailure("Customer is not allowed to access this page :(");
            return false;
        }
        return true;
    }

    @GetMapping("admin/category")
    public Response getCategory(@RequestParam("id") int id) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        CategoryController categoryController = CategoryController.getInstance();
        try {
            responseController.setSuccess(categoryController.getCategory(id));
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        return response;
    }

    @GetMapping("admin/show-categories")
    public Response getCategories() {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        CategoryController categoryController = CategoryController.getInstance();
        responseController.setSuccess(categoryController.getCategories());
        return response;
    }

    @PostMapping("admin/add-specific-discount")
    public Response addSpecificDiscount(@RequestBody Map<String, String> map) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        DiscountController discountController = DiscountController.getInstance();
        try {
            String name = map.get("name");
            int percentage = Integer.parseInt(map.get("percentage"));
            int id = Integer.parseInt(map.get("categoryId"));
            SpecificDiscount discount = discountController.getSpecificDiscount(name, percentage, id);
            adminController.addSpecificDiscount(discount, id);
        } catch (Exception e) {
            responseController.setFailure(e.getMessage());
            return response;
        }
        responseController.setSuccess("Discount has been added Successfully");
        return response;
    }

    @PostMapping("admin/add-overall-discount")
    public Response addOverallDiscount(@RequestBody OverallDiscount discount) {
        Response response = new Response();
        responseController.setResponse(response);
        if (!isValid())
            return response;
        adminController.addOverallDiscount(discount);
        responseController.setSuccess("Discount has been added Successfully");
        return response;
    }
}
