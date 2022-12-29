package com.epay.EPayment.Search;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.Service;

import java.util.Vector;

public class ConcreteSearch implements Search {
    @Override
    public Vector<Service> find(String sub) throws Exception {
        Vector<Service> result = new Vector<>();
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> categories = serviceData.getServices();
        for (Service category : categories) {
            Vector<String> company = category.getCompanies();
            for (int i = 0; i < company.size(); i++) {
                Service concreteService = category.clone(i + 1);
                String name = concreteService.getName();
                if (name.toLowerCase().contains(sub.toLowerCase())) {
                    result.add(concreteService);
                }
            }
        }
        if(result.isEmpty()){
            throw new Exception("There is no result :(") ;
        }
        return result;
    }

    public Vector<Service> listAll() {
        Vector<Service> result = new Vector<>();
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> categories = serviceData.getServices();
        for (Service category : categories) {
            Vector<String> company = category.getCompanies();
            for (int i = 0; i < company.size(); i++) {
                Service concreteService = category.clone(i + 1);
                result.add(concreteService);
            }
        }
        return result;
    }

}
