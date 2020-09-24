package com.JavaRestful.services;

import com.JavaRestful.models.ProductModel;

import org.springframework.stereotype.Service;


import com.JavaRestful.models.ProductModel;
import com.google.cloud.firestore.DocumentReference;

import java.util.concurrent.ExecutionException;

@Service
public class ProductService extends ServiceBridge {
    public ProductModel addProduct(ProductModel product)
    {
        product.setId(randomDocumentId("Products"));
        getFirebase().collection("Products").add(product);
        return product; 
    }

    public  ProductModel addProduct(String name, int price,int price2, String pic, String detail, int rootprice, String category){
        ProductModel  newproduct = new ProductModel(name, price, price2, pic, detail, rootprice, category);
        return addProduct(newproduct);
    }
    public  ProductModel getProductById(String id) throws InterruptedException, ExecutionException {

        ProductModel product =  getDocumentById("Products",id).toObject(ProductModel.class);
        return product;

    }
    public ProductModel putProduct(ProductModel producmodel){
        return  new ProductModel();
    }
   
    
}
