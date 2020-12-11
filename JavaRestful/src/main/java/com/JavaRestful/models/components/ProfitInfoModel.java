package com.JavaRestful.models.components;

public class ProfitInfoModel {
    private String idProduct ;
    private String nameProduct;
    private int quantity;
    private long totalIn;
    private long totalOut;
    private long totalWaiting;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(long totalIn) {
        this.totalIn = totalIn;
    }

    public long getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(long totalOut) {
        this.totalOut = totalOut;
    }

    public long getTotalWaiting() {
        return totalWaiting;
    }

    public void setTotalWaiting(long totalWaiting) {
        this.totalWaiting = totalWaiting;
    }

   
   



    
}
