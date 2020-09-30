package com.JavaRestful.models.requests.account;

public class ChangeAuthor {
    private  String id;
    private  boolean author;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }
}
