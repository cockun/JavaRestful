package com.JavaRestful.models.components;

import com.JavaRestful.services.HelpUtility;
import org.springframework.stereotype.Component;

import java.util.Random;


public class IncomeModel {
    private String id ;
    private String code;
    private String idIncome;
    private String incomeCategory;
    private float cost;
    private String note;
    private boolean status;

    public IncomeModel(){
        this.code = HelpUtility.getRandomCode("IC");
        this.incomeCategory = "Bán Hàng";
        this.cost = 0 ;
        this.status = false;
    }

    public boolean isStatus() {
        return status;
    }

    public String getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(String idIncome) {
        this.idIncome = idIncome;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
