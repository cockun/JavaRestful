package com.JavaRestful.models.components;

import com.JavaRestful.services.HelpUtility;
import org.springframework.stereotype.Component;

import java.util.Random;


public class IncomeModel {
    private String id ;
    private String code;
    private String incomeCategory;
    private float cost;
    private String note;

    public IncomeModel(){
        this.code = HelpUtility.getRandomCode("IC");
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

    public String getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(String incomeCategory) {
        this.incomeCategory = incomeCategory;
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
