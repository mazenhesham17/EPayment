package com.epay.EPayment.Discount;

import com.epay.EPayment.Service.Service;

public class SpecificDiscount extends Discount {
    Service category;

    public SpecificDiscount(String name, int percentage, Service category) {
        super(name, percentage);
        super.setAppliedCategory(category.getCategoryName());
        this.category = category;
    }

    public Service getCategory() {
        return category;
    }
}
