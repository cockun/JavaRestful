package com.JavaRestful.models.components;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class BillModel {
    private String id;
    private double total;
    private String date;
    private String nameUser;
    private String code;
    private Boolean isPay;

    //constructor
    public BillModel(){ isPay=false ;};
    public BillModel(double total,  String nameUser){
        this.total = total;
        this.nameUser = nameUser;
        this.isPay = false;
    }
    public BillModel(BillModel billModel){
        this.id = billModel.getId();
        this.total = billModel.getTotal();
        this.nameUser = billModel.getNameUser();
        this.code = billModel.getCode();
        this.date = billModel.getDate();
        this.isPay= billModel.getIsPay();
    }

    //get-set

    public String getId(){
        return id;
    }

    public  void setId(String id){
        this.id=id;
    }

    public double getTotal(){
        return total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public String getDate(){
        return  date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getNameUser(){
        return nameUser;
    }

    public void setNameUser(String nameUser){
        this.nameUser = nameUser;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public Boolean getIsPay(){
        return isPay;
    }

    public void setIsPay(Boolean isPay){
        this.isPay = isPay;
    }
}
