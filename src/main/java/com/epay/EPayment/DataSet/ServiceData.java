package com.epay.EPayment.DataSet;

import com.epay.EPayment.Service.Service;
import com.epay.EPayment.Util.Pair;

import java.util.HashMap;
import java.util.Vector;

public class ServiceData {
    static ServiceData serviceData = null;
    HashMap<Service, Pair<Integer, Vector<String>>> categories;
    Vector<Service> services;

    private ServiceData() {
        services = new Vector<>();
        categories = new HashMap<>();
    }

    public static ServiceData getInstance() {
        if (serviceData == null)
            serviceData = new ServiceData();
        return serviceData;
    }

    public HashMap<Service, Pair<Integer, Vector<String>>> getCategories() {
        return categories;
    }

    public Vector<Service> getServices() {
        return services;
    }
}
