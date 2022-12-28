package com.epay.EPayment.Discount;

import com.epay.EPayment.Models.Discount;
import com.epay.EPayment.Models.Service;

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
