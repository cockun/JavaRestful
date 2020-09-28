
package com.JavaRestful.models.components;


import org.springframework.stereotype.Component;



@Component
public class BillInfoModel {
    private String nameProduct;
    private String idProduct;
    private String code;
    private String detail;
    private String idPromotion;
    private double price;
    private double discount;
    private double priceRoot;
    private int quantity;

    //constructor
    public BillInfoModel(){};
    
    //get-set


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

    

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(String idPromotion) {
		this.idPromotion = idPromotion;
	}

}        