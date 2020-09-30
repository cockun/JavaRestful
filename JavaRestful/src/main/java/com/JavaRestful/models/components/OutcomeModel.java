package com.JavaRestful.models.components;

import com.JavaRestful.services.HelpUtility;
import org.springframework.stereotype.Component;


public class OutcomeModel {
    private String id ;
    private String code;
    private String idOutcome;
    private String outcomeCategory;
    private float cost;
    private String note;

    public OutcomeModel(){
        this.code = HelpUtility.getRandomCode("OC");
        this.idOutcome = "Nhập hàng";
        this.cost = 0 ;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
