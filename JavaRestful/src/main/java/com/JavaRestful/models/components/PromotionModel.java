package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class PromotionModel {
    private String id;
    private String promotionCode;
    private int discount;
    private boolean promotionCategory; // false giảm giá theo tiền ; true giảm giá theo %

    public PromotionModel(){
        this.discount = 0 ;
        this.promotionCategory  = false;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isPromotionCategory() {
        return promotionCategory;
    }

    public void setPromotionCategory(boolean promotionCategory) {
        this.promotionCategory = promotionCategory;
    }
}
