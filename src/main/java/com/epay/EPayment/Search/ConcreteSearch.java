package com.epay.EPayment.Search;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class ConcreteSearch implements Search {
    @Override
    public Vector<Service> find(String sub) throws Exception {
        Vector<Service> result = new Vector<>();
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> services = serviceData.getServices();
        for (Service service : services) {
            String name = service.getName();
            if (name.toLowerCase().contains(sub.toLowerCase())) {
                result.add(service);
            }
        }
        if (result.isEmpty()) {
            throw new Exception("There is no result :(");
        }
        return result;
    }

}
