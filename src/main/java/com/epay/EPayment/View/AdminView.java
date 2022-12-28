package com.epay.EPayment.View;

import com.epay.EPayment.Controller.RefundController;
import com.epay.EPayment.Models.Admin;
import com.epay.EPayment.Models.Refund;
import com.epay.EPayment.Models.RefundState;

import java.util.Vector;

public class AdminView {
    static AdminView adminView = null;
    Admin admin;

    private AdminView() {

    }

    public static AdminView getInstance() {
        if (adminView == null)
            adminView = new AdminView();
        return adminView;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Vector<Refund> showPendingRefunds() {
        boolean isFilled = false;
        RefundController refundController = RefundController.getInstance();
        RefundView refundView = RefundView.getInstance();
        Vector<Refund> refunds = admin.getRefunds();
        Vector<Refund> result = new Vector<>();
        for (Refund refund : refunds) {
            refundController.setRefund(refund);
            if (refundController.getRefundState() == RefundState.PENDING) {
                isFilled = true;
                System.out.println((result.size() + 1) + ".");
                refundView.setRefund(refund);
                refundView.showRefundDetails();
                result.add(refund);
            }
        }
        if (!isFilled) {
            System.out.println("There is no pending refunds");
        }
        return result;
    }

    public void showUpdates() {
        System.out.println("You have " + admin.getNotifications() + " new refund request");
    }
}
