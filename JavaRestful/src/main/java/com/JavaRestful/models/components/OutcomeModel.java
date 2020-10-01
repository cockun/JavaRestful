package com.JavaRestful.models.components;

import com.JavaRestful.services.HelpUtility;
import org.springframework.stereotype.Component;

@Component
public class OutcomeModel {
    private String id ;
    private String code;
    private String idOutcome;
    private int quantity;
    private String outcomeCategory;
    private long cost;
    private String note;

    public OutcomeModel(){
        this.code = HelpUtility.getRandomCode("OC");
        this.outcomeCategory = "Nhập hàng";
        this.cost = 0 ;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdOutcome() {
        return idOutcome;
    }

    public void setIdOutcome(String idOutcome) {
        this.idOutcome = idOutcome;
    }

    public String getOutcomeCategory() {
        return outcomeCategory;
    }

    public void setOutcomeCategory(String outcomeCategory) {
        this.outcomeCategory = outcomeCategory;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
