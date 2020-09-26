package com.JavaRestful.models.response.account;

import com.JavaRestful.models.components.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountInfoRes {
    private String id;
    private String user;

    private String name;
    private String phone;
    private String address;



    public AccountInfoRes(){ }
    public AccountInfoRes(AccountModel accountModel){
        this.id = accountModel.getId();
        this.user = accountModel.getUser();
        this.name = accountModel.getName();
        this.phone = accountModel.getPhone();
        this.address= accountModel.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
