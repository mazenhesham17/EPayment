package com.epay.EPayment.Search;

import com.epay.EPayment.Controller.ServiceController;
import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class ConcreteSearch implements Search {
    @Override
    public Vector<Service> find(String sub) throws Exception {
        Vector<Service> result = new Vector<>();
        ServiceController serviceController = ServiceController.getInstance() ;
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> services = serviceData.getServices();
        for (Service service : services) {
            serviceController.setService(service);
            String name = serviceController.getName();
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
