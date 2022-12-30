package com.epay.EPayment.Controller;

import com.epay.EPayment.DataSet.ServiceData;
import com.epay.EPayment.Models.Service;
import com.epay.EPayment.Util.Container;
import com.epay.EPayment.Util.Pair;
import com.epay.EPayment.View.CategoryWebView;

import java.util.Map;
import java.util.Vector;

public class CategoryController {
    static CategoryController categoryController = null;


    private CategoryController() {
    }

    public static CategoryController getInstance() {
        if (categoryController == null)
            categoryController = new CategoryController();
        return categoryController;
    }

    public Vector<Container> getCategories() {
        Vector<Container> containers = new Vector<>();
        ServiceData serviceData = ServiceData.getInstance();
        CategoryWebView categoryWebView = CategoryWebView.getInstance();
        for (Map.Entry<Service, Pair<Integer, Vector<String>>> entry : serviceData.getCategories().entrySet()) {
            containers.add(categoryWebView.showCategory(entry.getKey().getCategoryName(), entry.getValue().getFirst()));
        }
        return containers;
    }

    public Service getCategory(int id, int dummy) throws Exception {
        ServiceData serviceData = ServiceData.getInstance();
        if (id < 1 || id > serviceData.getCategories().size())
            throw new Exception("Id not in the range from 1 to " + serviceData.getCategories().size());
        for (Map.Entry<Service, Pair<Integer, Vector<String>>> entry : serviceData.getCategories().entrySet()) {
            if (id == entry.getValue().getFirst()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Container getCategory(int id) throws Exception {
        ServiceData serviceData = ServiceData.getInstance();
        if (id < 1 || id > serviceData.getCategories().size())
            throw new Exception("Id not in the range from 1 to " + serviceData.getCategories().size());
        CategoryWebView categoryWebView = CategoryWebView.getInstance();
        for (Map.Entry<Service, Pair<Integer, Vector<String>>> entry : serviceData.getCategories().entrySet()) {
            if (id == entry.getValue().getFirst()) {
                return categoryWebView.showCategory(entry.getKey().getCategoryName(), entry.getValue().getFirst());
            }
        }
        return null;
    }

}
