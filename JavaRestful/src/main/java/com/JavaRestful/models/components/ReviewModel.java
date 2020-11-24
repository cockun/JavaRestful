package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class ReviewModel {
    private String id ;
    private String idBill;
    private String idUser;
    private String idProduct;
    private String comment;
    private int reviewPoint ;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }



    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(int reviewPoint) {
        this.reviewPoint = reviewPoint;
    }
}
