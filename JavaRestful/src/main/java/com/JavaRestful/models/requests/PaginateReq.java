package com.JavaRestful.models.requests;

public class PaginateReq {
    private int page;
    private int limit;
    private String field;
    private boolean optionSort;
    private String value = "";
    private boolean optionSearch;

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

    public boolean isOptionSearch() {
        return optionSearch;
    }

    public void setOptionSearch(boolean optionSearch) {
        this.optionSearch = optionSearch;
    }
}
