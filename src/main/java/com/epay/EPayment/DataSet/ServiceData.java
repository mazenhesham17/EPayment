package com.epay.EPayment.DataSet;

import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Util.Pair;

import java.util.HashMap;
import java.util.Vector;

public class ServiceData {
    HashMap<Service,Pair<Vector<String>,Vector<Payment>>>categories ;
    static ServiceData serviceData = null;
    Vector<Service> services;

    private ServiceData() {
        services = new Vector<>();
        categories = new HashMap<>();
    }
    public HashMap<Service, Pair<Vector<String>, Vector<Payment>>> getCategories() {
        return categories;
    }
    public static ServiceData getInstance() {
        if (serviceData == null)
            serviceData = new ServiceData();
        return serviceData;
    }

    public Vector<Service> getServices() {
        return services;
    }
}
