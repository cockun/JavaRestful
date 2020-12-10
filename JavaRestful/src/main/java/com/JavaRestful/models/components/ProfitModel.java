package com.JavaRestful.models.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProfitModel {
    private List<ProfitInfoModel> listProduct;
    private long totalIn;
    private long totalOut;

    public List<ProfitInfoModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProfitInfoModel> listProduct) {
        this.listProduct = listProduct;
    }

    public long getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(long totalIn) {
        this.totalIn = totalIn;
    }

    public long getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(long totalOut) {
        this.totalOut = totalOut;
    }


   

  

 
}
