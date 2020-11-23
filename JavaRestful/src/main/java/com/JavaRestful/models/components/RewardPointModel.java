package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class RewardPointModel {
  private String idAccount;
  private float pointAvailable;
  private float pointRank;



    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public float getPointAvailable() {
        return pointAvailable;
    }

    public void setPointAvailable(float pointAvailable) {
        this.pointAvailable = pointAvailable;
    }

    public float getPointRank() {
        return pointRank;
    }

    public void setPointRank(float pointRank) {
        this.pointRank = pointRank;
    }
}
