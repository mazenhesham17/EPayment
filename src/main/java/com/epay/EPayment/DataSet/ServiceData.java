package com.epay.EPayment.DataSet;

import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class ServiceData {

    static ServiceData serviceData = null;
    Vector<Service> services;

    private ServiceData() {
        services = new Vector<>();
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
