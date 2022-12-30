package com.epay.EPayment.Controller;

import com.epay.EPayment.Discount.SpecificDiscount;
import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.DiscountData;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.View.DiscountWebView;

import java.util.Map;
import java.util.Vector;

public class DiscountController {
    static DiscountController discountController = null;
    Discount discount;
    DiscountData discountData;

    private DiscountController() {
    }

    public static DiscountController getInstance() {
        if (discountController == null)
            discountController = new DiscountController();
        return discountController;
    }

    public void setDiscountData(DiscountData discountData) {
        this.discountData = discountData;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void addCategory(Service service) {
        discountData.getSpecificDiscounts().put(service.getClass(), new Vector<>());
    }

    public void addSpecificDiscount(Discount discount, Service service) {
        discountData.getSpecificDiscounts().get(service.getClass()).add(discount);
    }

    public void addOverallDiscount(Discount discount) {
        discountData.getDiscounts().add(discount);
    }

    public Vector<Container> showAll() throws Exception {
        DiscountWebView discountWebView = DiscountWebView.getInstance();
        Vector<Discount> discounts = discountData.getDiscounts();
        Vector<Container> containers = new Vector<>() ;
        for ( Discount concreteDiscount : discounts ){
            containers.add(discountWebView.showDiscount(concreteDiscount.getName(),concreteDiscount.getPercentage(),concreteDiscount.getAppliedCategory())) ;
        }
        for (Map.Entry<Class, Vector<Discount>> entry : discountData.getSpecificDiscounts().entrySet()) {
            discounts = entry.getValue();
            for ( Discount concreteDiscount : discounts ){
                containers.add(discountWebView.showDiscount(concreteDiscount.getName(),concreteDiscount.getPercentage(),concreteDiscount.getAppliedCategory())) ;
            }
        }
        if ( containers.isEmpty() ){
            throw new Exception("You do not have discount :(");
        }else{
            return containers ;
        }
    }

    public Vector<Discount> useDiscounts(Service service) {
        Vector<Discount> result = new Vector<>();
        int percentage = 0;
        Vector<Discount> discounts = discountData.getDiscounts();
        for (int i = 0; i < discounts.size(); i++) {
            Discount discount = discounts.get(i);
            percentage += discount.getPercentage();
            if (percentage <= 90) {
                result.add(discount);
                discounts.remove(discount);
            }
        }
        discounts = discountData.getSpecificDiscounts().get(service.getClass());
        for (int i = 0; i < discounts.size(); i++) {
            Discount discount = discounts.get(i);
            percentage += discount.getPercentage();
            if (percentage <= 90) {
                result.add(discount);
                discounts.remove(discount);
            }
        }
        return result;
    }
    public SpecificDiscount getSpecificDiscount(String name , int percentage , int id) throws Exception {
        CategoryController categoryController = CategoryController.getInstance() ;
        Service service = categoryController.getCategory(id,0) ;
        return new SpecificDiscount(name,percentage,service);
    }
}
