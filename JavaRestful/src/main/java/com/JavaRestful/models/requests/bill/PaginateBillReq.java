package com.JavaRestful.models.requests.bill;

public class PaginateBillReq {
    private String field;
    private String value;
    private String dateBegin;
    private String dateEnd;
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

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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

    public PaginateBillReq(String field, String value, String dateBegin, String dateEnd, int page, int limit) {
        this.field = field;
        this.value = value;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.page = page;
        this.limit = limit;
    }

}
