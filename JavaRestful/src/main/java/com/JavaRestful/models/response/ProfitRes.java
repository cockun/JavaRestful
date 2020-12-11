package com.JavaRestful.models.response;

public class ProfitRes {
    private String name;
    private String userName;
    private String idProduct;
    private double rootPrice;
    private double discount;
    private int quantity;
    private long total;
    private boolean isPay;
    private String date;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public double getRootPrice() {
        return rootPrice;
    }

    public void setRootPrice(double rootPrice) {
        this.rootPrice = rootPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean isPay) {
        this.isPay = isPay;
    }

}
