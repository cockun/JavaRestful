package com.JavaRestful.models.components;

import java.util.ArrayList;


import com.JavaRestful.models.requests.bill.BillOrderReq;
import org.springframework.stereotype.Component;

@Component
public class BillModel {
    private String id;
    private String code;
    private String nameUser;
    private long total;
    private String date;
    private ArrayList<BillInfoModel> billInfoModel;
    private long discount;
    private String codePromotion;
    private Boolean isPay;


    public BillModel(){ isPay=false ;};

    public BillModel(long total,  String nameUser){
        this.total = total;
        this.nameUser = nameUser;
        this.isPay = false;
    }

    public BillModel(BillOrderReq billOrderReq , ArrayList<BillInfoModel> billInfoModel , long total){
        this.nameUser = billOrderReq.getNameUser();
        this.total = total;
        this.isPay = false;
        this.billInfoModel = billInfoModel;

    }



    public BillModel(BillModel billModel){
        this.id = billModel.getId();
        this.total = billModel.getTotal();
        this.nameUser = billModel.getNameUser();
        this.code = billModel.getCode();
        this.date = billModel.getDate();
        this.isPay= billModel.getPay();
        this.billInfoModel= billModel.billInfoModel;
    }








    public String getCodePromotion() {
        return codePromotion;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getPay() {
        return isPay;
    }

    public ArrayList<BillInfoModel> getBillInfoModel() {
        return billInfoModel;
    }

    public void setBillInfoModel(ArrayList<BillInfoModel> billInfoModel) {
        this.billInfoModel = billInfoModel;
    }



    public void setPay(Boolean pay) {
        isPay = pay;
    }
}
