package com.epay.EPayment.Discount;

public class OverallDiscount extends Discount {

    public OverallDiscount(String name, int percentage) {
        super(name, percentage);
        super.setAppliedCategory("any service");
    }
}
