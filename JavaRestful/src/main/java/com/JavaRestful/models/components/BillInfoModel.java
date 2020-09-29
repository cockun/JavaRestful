
package com.JavaRestful.models.components;




import org.springframework.stereotype.Component;



@Component
public class BillInfoModel {
    private String nameProduct;
    private String idProduct;
    private String code;
    private double price;
    private double discount;
    private double priceRoot;
    private long total;


    private int quantity;

    //constructor
    public BillInfoModel(){}




    //get-set


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPriceRoot() {
        return priceRoot;
    }

    public void setPriceRoot(double priceRoot) {
        this.priceRoot = priceRoot;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}