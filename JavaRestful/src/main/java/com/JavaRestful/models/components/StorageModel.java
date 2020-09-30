package com.JavaRestful.models.components;

import com.JavaRestful.models.requests.outcome.InputStorageReq;

public class StorageModel {
    private String id ;
    private String idProduct;
    private int quantity;
    private String note;

    public StorageModel(){ }
    public StorageModel(InputStorageReq inputStorageReq){
        this.idProduct = inputStorageReq.getIdProduct();
        this.quantity = inputStorageReq.getQuantity();
        this.note = inputStorageReq.getNote();
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
