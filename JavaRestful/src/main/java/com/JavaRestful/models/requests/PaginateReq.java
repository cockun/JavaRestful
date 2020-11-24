package com.JavaRestful.models.requests;

public class PaginateReq {
    private String field;
    private String value ;
    private boolean optionSort;
    private String fieldSort;
    private int page;
    private int limit;

    public PaginateReq(String field, String value , boolean optionSort,String fieldSort,int page,int limit ){
        this.field = field;
        this.value= value;
        this.optionSort = optionSort;
        this.fieldSort = fieldSort;
        this.page = page;
        this.limit = limit;
    }
    public String getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(String fieldSort) {
        this.fieldSort = fieldSort;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isOptionSort() {
        return optionSort;
    }

    public void setOptionSort(boolean optionSort) {
        this.optionSort = optionSort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
