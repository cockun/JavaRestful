package com.JavaRestful.models.components;

import com.JavaRestful.models.requests.outcome.InputStorageReq;
import com.JavaRestful.services.HelpUtility;

public class StorageModel {
    private String id ;
    private String code;
    private String idProduct;
    private int quantity;
    private String note;
    private String date;

    public StorageModel(){ }
    public StorageModel(InputStorageReq inputStorageReq){
        this.idProduct = inputStorageReq.getIdProduct();
        this.quantity = inputStorageReq.getQuantity();
        this.note = inputStorageReq.getNote();
        this.code = HelpUtility.getRandomCode("ST");
        this.date = java.time.LocalDate.now().toString();
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
