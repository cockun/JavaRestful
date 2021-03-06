package com.JavaRestful.models.response.bill;

import com.JavaRestful.models.components.BillInfoModel;
import com.JavaRestful.models.components.BillModel;

import java.util.ArrayList;

public class BillRes {
    private String id;
    private String code;
    private String nameUser;
    private String codePromotion;
    private ArrayList<BillInfoResUser> listBillInfoRes;
    private long discount;
    private long total;
    private String date;
    private Boolean isPay;
    private String address;
    private String phone;
    private String email;


    public BillRes(BillModel billModel){
        this.id = billModel.getId();
        this.total = billModel.getTotal();
        this.date = billModel.getDate();
        this.nameUser = billModel.getNameUser();
        this.code =  billModel.getCode();
        this.discount = billModel.getDiscount();
        this.isPay = billModel.getPay();
        this.codePromotion = billModel.getCodePromotion();
        ArrayList<BillInfoResUser> list = new ArrayList<>();
        for (BillInfoModel billInfoModel : billModel.getBillInfoModel()) {
            BillInfoResUser billInfoResUser = new BillInfoResUser();

            billInfoResUser.setQuantity(billInfoModel.getQuantity());
            billInfoResUser.setCode(billInfoModel.getCode());
            billInfoResUser.setDiscount(billInfoModel.getDiscount());
            billInfoResUser.setIdProduct(billInfoModel.getIdProduct());
            billInfoResUser.setNameProduct(billInfoModel.getNameProduct());
            billInfoResUser.setPrice(billInfoModel.getPrice());
            billInfoResUser.setTotal(billInfoModel.getTotal());
            list.add(billInfoResUser);
        }
        this.listBillInfoRes = list;

    }

    public String getCodePromotion() {
        return codePromotion;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
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

    public ArrayList<BillInfoResUser> getListBillInfoRes() {
        return listBillInfoRes;
    }

    public void setListBillInfoRes(ArrayList<BillInfoResUser> listBillInfoRes) {
        this.listBillInfoRes = listBillInfoRes;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
