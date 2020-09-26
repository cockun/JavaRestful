package com.JavaRestful.models.requests.account;

public class ChangePassword {
    private String idUser;



    private String password;
    private String passwordNew;

    public String getUser() {
        return idUser;
    }

    public void setUser(String user) {
        this.idUser = user;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}