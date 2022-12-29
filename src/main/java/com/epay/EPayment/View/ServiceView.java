package com.epay.EPayment.View;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.Form;
import com.epay.EPayment.Models.Payment;
import com.epay.EPayment.Models.Service;

import java.util.HashMap;
import java.util.Vector;

public class ServiceView {
    static ServiceView serviceView = null;
    Service service;

    private ServiceView() {
    }

    public static ServiceView getInstance() {
        if (serviceView == null)
            serviceView = new ServiceView();
        return serviceView;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void showCategories() {
        ServiceData serviceData = ServiceData.getInstance();
        Vector<Service> services = serviceData.getServices();
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).getCategoryName());
        }
    }

    public void showServices(Vector<Service> services) {
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).getName());
        }
    }
    public HashMap<String, Object> convert(String name , int id , Form form , Vector<Payment> payments ){
        HashMap<String,Object> json = new HashMap<>() ;
        json.put("name",name) ;
        json.put("id",id) ;
        json.put("form",form) ;
        json.put("supported payments",payments) ;
        return json ;
    }
}
