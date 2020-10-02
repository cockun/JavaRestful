package com.JavaRestful.services;

import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.models.components.CategoryModel;
import com.JavaRestful.models.components.ProductModel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryService extends ServiceBridge {

    public CollectionReference getCategoryCollection (){

        return getFirebase().collection("Category");
    }

    public DocumentReference getCategoryDocumentById(String id )   {
        return getDocumentById("Category",id);
    }


    public List<CategoryModel> getAllCategories() throws ExecutionException, InterruptedException {

        return getCategoryCollection().get().get().toObjects((CategoryModel.class));

    }

    public CategoryModel getCategoryById(String id ) throws ExecutionException, InterruptedException {
        return getCategoryDocumentById(id).get().get().toObject(CategoryModel.class);
    }

    public CategoryModel getCategoryByIdProduct(String idProduct ) throws ExecutionException, InterruptedException {
        ProductService  productService= new ProductService();
        ProductModel productModel =  productService.getProductByIdAdmin(idProduct);
        return getCategoryCollection().whereEqualTo("id",productModel.getIdcategory()).get().get().toObjects(CategoryModel.class).get(0);
    }


}
