package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class BillModel {
    private String id;
    private double total;
    private String date;
    private String idUser;

    //constructor
    public BillModel(){};
    public BillModel(String id ,double total, String date , String idUser){
        this.id = id;
        this.total = total;
        this.date = date;
        this.idUser = idUser;
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

    public String getIdUser(){
        return idUser;
    }

    public void setIdUser(String idUser){
        this.idUser = idUser;
    }
}
