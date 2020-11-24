package com.JavaRestful.models.response.account;

import com.JavaRestful.models.components.ProductModel;

public class ProductInfoRes {
    private String id;
    private String name;
    private String code;
    private int price;
    private int discount;
    private String pic;
    private String detail;
    private String writer;
    private String idcategory;
    private float reviewPoint;

    public float getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(float reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public ProductInfoRes(ProductInfoResAdmin productModel){
        this.id = productModel.getId();
        this.name= productModel.getName();
        this.code = productModel.getCode();
        this.price = productModel.getPrice();
        this.discount = productModel.getDiscount();
        this.pic = productModel.getPic();
        this.detail = productModel.getDetail();
        this.writer = productModel.getWriter();
        this.idcategory = productModel.getIdcategory();
        this.reviewPoint = productModel.getReviewPoint();
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

    public ProductInfoRes(){}

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }


}
