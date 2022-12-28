package com.epay.EPayment.View;

import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.DiscountData;

import java.util.Map;
import java.util.Vector;

public class DiscountView {
    static DiscountView discountView = null;

    DiscountData discountData;

    private DiscountView() {

    }

    public static DiscountView getInstance() {
        if (discountView == null)
            discountView = new DiscountView();
        return discountView;
    }

    public void setDiscountData(DiscountData discountData) {
        this.discountData = discountData;
    }

    public boolean showList(Vector<Discount> discounts) {
        boolean printed = false;
        for (Discount discount : discounts) {
            printed = true;
            System.out.println(discount.getName() + " : " + discount.getPercentage() + "% OFF on " + discount.getAppliedCategory());
        }
        return printed;
    }

    public void showAll() {
        Vector<Discount> discounts = discountData.getDiscounts();
        boolean isFilled = false;
        isFilled |= showList(discounts);
        for (Map.Entry<Class, Vector<Discount>> entry : discountData.getSpecificDiscounts().entrySet()) {
            discounts = entry.getValue();
            isFilled |= showList(discounts);
        }
        if (!isFilled) {
            System.out.println("You do not have discount :(");
        }
    }

}
