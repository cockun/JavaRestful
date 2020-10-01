package com.JavaRestful.services;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.CategoryModel;
import com.JavaRestful.models.components.ProductModel;

import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.products.ProductsInfoChange;
import com.JavaRestful.models.requests.products.SearchProduct;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class ProductService extends ServiceBridge {
    public CollectionReference getProductCollection (){

        return getFirebase().collection("Products");
    }

    public DocumentReference getProductDocumentById(String id )   {
        return getDocumentById("Products",id);
    }
    public ProductModel getProductDocumentByUser(String name) {
        try {
            List<ProductModel> productmodel = getProductCollection().whereEqualTo("name",name).get().get().toObjects((ProductModel.class));
            if(productmodel.isEmpty()){
                return null;
            }else {
                return productmodel.get(0);
            }
        }catch (Exception  e){
            return null;
        }


    }
    public List<ProductModel> findProduct(String name){

        try{
            
           return getProductCollection().whereGreaterThanOrEqualTo("name", name).limit(5).get().get().toObjects(ProductModel.class);
        }catch (Exception e){
            return null;
        }

    }
    
    public  ProductModel getProductDocumentByIdProduct(String idProduct) {
        try {
            ProductModel productmodel = getProductCollection().whereEqualTo("id",idProduct).get().get().toObjects((ProductModel.class)).get(0);
            return productmodel;
        }catch (Exception  e){
            return null;
        }
    }
 
    public ProductModel addProductModel (ProductModel productmodel ) throws InterruptedException, ExecutionException
    {
        if(productmodel.getName()==null || !getFirebase().collection("Products").whereEqualTo("name", productmodel.getName()).get().get().toObjects(ProductModel.class).isEmpty() )
        {
            return null;
        }
        else
        {
            //category
            List<ProductModel> product = getFirebase().collection("Category").whereEqualTo("name", productmodel.getIdcategory()).get().get().toObjects(ProductModel.class);
            if(product.isEmpty()){
                CategoryModel category = new CategoryModel();
                category.setId(randomDocumentId("Category"));
                category.setName(productmodel.getIdcategory());
                getFirebase().collection("Category").document(category.getId()).set(category);
                productmodel.setIdcategory(category.getId());
            }
            else
            {
                productmodel.setIdcategory(product.get(0).getId());
            }
            // add
            if(productmodel.getCode()==null)
            {
                productmodel.setCode(HelpUtility.getRandomCode("SP"));
            }
            productmodel.setId(randomDocumentId("Products"));
            getProductDocumentById(productmodel.getId()).set(productmodel);
            return productmodel;
        }
    }
    public List<ProductModel> addMultiProduct(List<ProductModel> products) throws InterruptedException, ExecutionException
    {
        for(ProductModel product : products)
        {
            addProductModel(product);
        }
        return products;
    }
    
    public  ProductInfoRes getProductById(String id) throws InterruptedException, ExecutionException {

        return  getDocumentById("Products",id).get().get().toObject(ProductInfoRes.class);

    }

    public  ProductModel getProductByIdAdmin(String id) throws InterruptedException, ExecutionException {

        return  getDocumentById("Products",id).get().get().toObject(ProductModel.class);

    }
    public  List<ProductInfoRes> getAllProducts() throws ExecutionException, InterruptedException {
        return getProductCollection().orderBy("name").get().get().toObjects(ProductInfoRes.class);

    }

    public ApiResponseData<List<ProductInfoRes>> searchProduct(SearchProduct searchProduct) throws ExecutionException, InterruptedException {
        return new  ApiResponseData<>(getProductCollection().orderBy(searchProduct.getFilter()).startAt(searchProduct.getValue()).endAt(searchProduct.getValue()+'\uf8ff').get().get().toObjects(ProductInfoRes.class));
    }

    public  List<ProductModel> getAllProductsByAdmin() throws ExecutionException, InterruptedException {
        return getProductCollection().orderBy("name").get().get().toObjects(ProductModel.class);

    }

    public ProductModel putProduct(ProductsInfoChange productmodel) throws InterruptedException, ExecutionException {
        ProductModel product = getDocumentById("Products", productmodel.getId()).get().get().toObject(ProductModel.class);
        product.changeProduct(productmodel)   ;
        getProductDocumentById(productmodel.getId()).set(product);
        return product.changeProduct(productmodel);
    }
    

    public List<ProductInfoRes> paginateProductOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }

        if(page.getField() == "" || page.getField() == null){
            page.setField("id");
        }

        if(page.isOptionSort()){
            try {
                DocumentSnapshot start = getProductCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getProductCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(ProductInfoRes.class);
            }catch (Exception e){
                return null;
            }
        }else {
            try {
                DocumentSnapshot start = getProductCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getProductCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(ProductInfoRes.class);
            }catch (Exception e){
                return null;
            }


        }

    }

    public List<ProductInfoRes> paginateProductSearchField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        DocumentSnapshot start = getProductCollection().whereGreaterThanOrEqualTo(page.getField(),page.getValue()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
        Query coc = getProductCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return  coc.get().get().toObjects(ProductInfoRes.class);

    }
    public ProductModel deleteProduct(String id ) throws InterruptedException, ExecutionException
    {
        ProductModel product;
        product = getDocumentById("Products",id).get().get().toObject(ProductModel.class);
        deleteDocument("Products", id);
        return product;
        
    }


}
