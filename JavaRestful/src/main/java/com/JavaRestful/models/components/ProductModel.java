package com.JavaRestful.models.components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductModel {
    private String id;
    private String name;

    private int price;
    private int price2;
    private String pic;
    private String detail;
    private int rootprice;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRootprice() {
        return rootprice;
    }

    public void setRootprice(int rootprice) {
        this.rootprice = rootprice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   

	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ProductModel(){}

    public ProductModel(String name, int price,int price2, String pic, String detail, int rootprice, String category) {
        this.name = name;
        this.price = price;
        this.price2 = price2;
        this.pic = pic;
        this.detail = detail;
        this.rootprice = rootprice;
        this.category = category;
    }
}
