package com.epay.EPayment.Search;

import com.epay.EPayment.Models.Service;

import java.util.Vector;

public interface Search {
    Vector<Service> find(String sub) throws Exception;

}
