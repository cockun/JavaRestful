package com.JavaRestful.models.components;


import org.springframework.stereotype.Component;

@Component
public class BIllInfoModel {
    private String id ;
    private String idBill;
    private String idProduct;
    private double price;
    private double price2;
    private double priceRoot;
    private String date;

    //constructor
    public BIllInfoModel(){};
    public BIllInfoModel(String id, String idBill , String idProduct , double price , double price2 , double priceRoot  , String date){
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.price = price;
        this.price2 = price2;
        this.priceRoot = priceRoot;
        this.date = date;
    }

    //get-set

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getIdBill(){
        return idBill;
    }

    public void setIdBill(String idBill){
        this.idBill = idBill;
    }

    public String getIdProduct(){
        return idProduct;
    }

    public void setIdProduct(String idProduct){
        this.idProduct = idProduct;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice2(){
        return price2;
    }

    public void setPrice2(double price2){
        this.price2=price2;
    }

    public double getPriceRoot(){
        return priceRoot;
    }

    public void setPriceRoot(double priceRoot){
        this.priceRoot = priceRoot;
    }    

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }
}
