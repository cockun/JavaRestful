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

        return  getDocumentById("Products",id).get().get().toObject(ProductModel.class);

    }
    public ProductModel putProduct(ProductModel producmodel){
        getFirebase().collection("Products").document(producmodel.getId()).set(producmodel);
        return producmodel;

    }
    public ProductModel deleteProduct(String id) throws ExecutionException, InterruptedException {
        ProductModel productmodel;
        productmodel = getDocumentById("Products",id).get().get().toObject(ProductModel.class);
        deleteDocument("Prodcuts",id);
        return productmodel;
    }
    
}
