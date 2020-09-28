package com.JavaRestful.models.requests.bill;

import com.JavaRestful.models.components.BillInfoModel;

import java.util.ArrayList;

public class BillOrderReq {
    private String id;
    private String nameUser;
    private String promotionCode;
    private ArrayList<BillOrderReqInfo> billOrderReqInfos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }



    public ArrayList<BillOrderReqInfo> getBillOrderReqInfos() {
        return billOrderReqInfos;
    }

    public void setBillOrderReqInfos(ArrayList<BillOrderReqInfo> billOrderReqInfos) {
        this.billOrderReqInfos = billOrderReqInfos;
    }
}
