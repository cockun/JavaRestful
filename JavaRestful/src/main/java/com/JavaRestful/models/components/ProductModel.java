package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

import com.JavaRestful.models.requests.products.ProductsInfoChange;
import com.JavaRestful.services.HelpUtility;

@Component
public class ProductModel {
    private String id;
    private String name;
    private String code;
    private int price;
    private int discount;
    private String pic;
    private String detail;
    private int rootprice;
    private String idSupplier;
    private String writer;
    private String idcategory;
    private String date;



    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRootprice() {
        return rootprice;
    }

    public void setRootprice(int rootprice) {
        this.rootprice = rootprice;
    }

    public ProductModel() {
        this.code = HelpUtility.getRandomCode("SP");
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public ProductModel changeProduct(ProductModel product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.detail = product.getDetail();
        this.pic = product.getPic();
        this.code = product.getCode();
        this.writer = product.getWriter();
        this.idSupplier = product.getIdSupplier();
        this.rootprice = product.getRootprice();
        return this;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

}
