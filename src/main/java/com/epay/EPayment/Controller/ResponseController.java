package com.epay.EPayment.Controller;

import com.epay.EPayment.Models.Response;
import com.epay.EPayment.Models.Service;

public class ResponseController {
    static ResponseController responseController = null;
    Response response ;

    private ResponseController() {
    }

    public static ResponseController getInstance() {
        if (responseController == null)
            responseController = new ResponseController();
        return responseController;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setSuccess( String message ){
        response.setStatus(true);
        response.setMessage(message);
    }
    public void setSuccess( String message , Object obj ){
        setSuccess(message);
        response.setObject(obj);
    }

    public void setSuccess( Object obj ){
        setSuccess("");
        response.setObject(obj);
    }

    public void setFailure( String message ){
        response.setStatus(false);
        response.setMessage(message);
    }
}
