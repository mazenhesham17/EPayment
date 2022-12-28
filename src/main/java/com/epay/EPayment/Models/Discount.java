package com.epay.EPayment.Models;

public class Discount {
    String appliedCategory;
    String name;
    int percentage;

    public Discount(String name, int percentage) {
        this.percentage = percentage;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getAppliedCategory() {
        return appliedCategory;
    }

    public void setAppliedCategory(String appliedCategory) {
        this.appliedCategory = appliedCategory;
    }


}
