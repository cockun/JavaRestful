package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class SupplierModel   {
    private String id ;
    private String name;
    private String address;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresss() {
        return address;
    }

    public void setAddresss(String addresss) {
        this.address = addresss;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
}
