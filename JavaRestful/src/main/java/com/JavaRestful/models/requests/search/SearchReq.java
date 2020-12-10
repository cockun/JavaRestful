package com.JavaRestful.models.requests.search;

public class SearchReq {
    private String field;
    private String value;
    private String value2;
    

    public SearchReq(String field , String value , String value2){
        this.field = field;
        this.value = value;
        this.value2 = value2;
    }
    public SearchReq(String field , String value ){
        this.field = field;
        this.value = value;
     
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
