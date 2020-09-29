package com.JavaRestful.models.requests.bill;

public class BillOrderReqInfo {
    private String idProduct;
    private int  quantity;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
