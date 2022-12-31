package com.epay.EPayment.Discount;

import java.util.HashMap;
import java.util.Vector;

public class DiscountData {
    Vector<Discount> discounts;
    HashMap<Class, Vector<Discount>> specificDiscounts;

    public DiscountData() {
        discounts = new Vector<>();
        specificDiscounts = new HashMap<>();
    }

    public HashMap<Class, Vector<Discount>> getSpecificDiscounts() {
        return specificDiscounts;
    }

    public Vector<Discount> getDiscounts() {
        return discounts;
    }
}
