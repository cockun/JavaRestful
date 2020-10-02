package com.JavaRestful.models.requests.bill;

public class PutStatusBill {
    private String id;
    private boolean pay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }
}
