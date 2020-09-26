package com.JavaRestful.models.components;

import com.JavaRestful.services.HelpUtility;
import org.springframework.stereotype.Component;

@Component
public class BIllInfoModel {
    private String id ;
    private String idBill;
    private String idProduct;
    private double price;
    private double discount;
    private double priceRoot;
    private String date;
    private String code;

    //constructor
    public BIllInfoModel(){};
    public BIllInfoModel(String idBill , String idProduct , double price , double discount , double priceRoot ){
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.price = price;
        this.discount = discount;
        this.priceRoot = priceRoot;

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

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount=discount;
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

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code= code;
    }
}
