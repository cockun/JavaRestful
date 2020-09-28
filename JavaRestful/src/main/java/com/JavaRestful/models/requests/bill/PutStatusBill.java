package com.JavaRestful.models.requests.bill;

public class PutStatusBill {
    private String id;
    private boolean isPay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }
}
