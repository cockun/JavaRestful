package com.JavaRestful.models.requests.account;

public class PaginateAccountReq {
    private String field;
    private String value;
    private int page;
    private int limit;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public PaginateAccountReq(String field, String value, int page, int limit) {
        this.field = field;
        this.value = value;
        this.page = page;
        this.limit = limit;
    }

}
