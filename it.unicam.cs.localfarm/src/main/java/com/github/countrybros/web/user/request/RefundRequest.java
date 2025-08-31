package com.github.countrybros.web.user.request;

public class RefundRequest {
    private int orderId;
    private String email;
    private int userId;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }
}
