package com.epay.EPayment.Controller;

import com.epay.EPayment.Util.Container;

public class ContainerController {
    static ContainerController containerController = null;
    Container container;

    private ContainerController() {
    }

    public static ContainerController getInstance() {
        if (containerController == null)
            containerController = new ContainerController();
        return containerController;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public void put(String key, Object value) {
        container.getContainer().put(key, value);
    }

}
