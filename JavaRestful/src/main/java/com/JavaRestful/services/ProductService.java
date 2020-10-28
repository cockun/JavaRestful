package com.JavaRestful.services;

import com.JavaRestful.models.components.CategoryModel;
import com.JavaRestful.models.components.ProductModel;

import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.products.ProductsInfoChange;

import com.JavaRestful.models.response.account.ProductInfoRes;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductService extends ServiceBridge {
    public CollectionReference getProductCollection() {

        return getFirebase().collection("Products");
    }

    public DocumentReference getProductDocumentById(String id) {
        return getDocumentById("Products", id);
    }

    public ProductModel getProductDocumentByUser(String name) {
        try {
            List<ProductModel> productmodel = getProductCollection().whereEqualTo("name", name).get().get()
                    .toObjects((ProductModel.class));
            if (productmodel.isEmpty()) {
                return null;
            } else {
                return productmodel.get(0);
            }
        } catch (Exception e) {
            return null;
        }

    }

    public List<ProductModel> findProduct(String name) {

        try {

            return getProductCollection().whereGreaterThanOrEqualTo("name", name).limit(5).get().get()
                    .toObjects(ProductModel.class);
        } catch (Exception e) {
            return null;
        }

    }



    public List<ProductInfoRes> searchProductByField(String field, String value) {
        try {
            return getProductCollection().whereEqualTo(field, value).get().get().toObjects(ProductInfoRes.class);

        } catch (Exception e) {
            return null;
        }
    }





    public ProductModel addProductModel(ProductModel productmodel) throws InterruptedException, ExecutionException {
        if (productmodel.getName() == null || !getFirebase().collection("Products")
                .whereEqualTo("name", productmodel.getName()).get().get().toObjects(ProductModel.class).isEmpty()) {
            return null;
        } else {
            // category
            List<ProductModel> product = getFirebase().collection("Category")
                    .whereEqualTo("name", productmodel.getIdcategory()).get().get().toObjects(ProductModel.class);
            if (product.isEmpty()) {
                CategoryModel category = new CategoryModel();
                category.setId(randomDocumentId("Category"));

                category.setName(productmodel.getIdcategory());
                getFirebase().collection("Category").document(category.getId()).set(category);
                productmodel.setIdcategory(category.getId());
            } else {
                productmodel.setIdcategory(product.get(0).getId());
            }
            // add

            productmodel.setCode(HelpUtility.getRandomCode("SP"));

            productmodel.setDate(java.time.LocalDate.now().toString());
            productmodel.setId(randomDocumentId("Products"));
            getProductDocumentById(productmodel.getId()).set(productmodel);
            return productmodel;
        }
    }

    public List<ProductModel> addMultiProduct(List<ProductModel> products)
            throws InterruptedException, ExecutionException {
        for (ProductModel product : products) {
            addProductModel(product);
        }
        return products;
    }

    public ProductInfoRes getProductById(String id) throws InterruptedException, ExecutionException {

        ProductInfoRes productInfoRes = getDocumentById("Products", id).get().get().toObject(ProductInfoRes.class);
        CategoryModel categoryModel = getFirebase().collection("Category").document(productInfoRes.getIdcategory())
                .get().get().toObject(CategoryModel.class);
        productInfoRes.setIdcategory(categoryModel.getName());
        return productInfoRes;

    }



    public ProductInfoRes getIdProductByCode(String code) throws ExecutionException, InterruptedException {
        return getProductCollection().whereEqualTo("code", code).get().get().toObjects(ProductInfoRes.class).get(0);


    }

    public ProductModel getProductByIdAdmin(String id) throws InterruptedException, ExecutionException {

        ProductModel productModel = getDocumentById("Products", id).get().get().toObject(ProductModel.class);
        CategoryModel categoryModel = getFirebase().collection("Category").document(productModel.getIdcategory()).get()
                .get().toObject(CategoryModel.class);
        productModel.setIdcategory(categoryModel.getName());
        return productModel;

    }

    public List<ProductInfoRes> getAllProducts() throws ExecutionException, InterruptedException {
        return getProductCollection().orderBy("name").get().get().toObjects(ProductInfoRes.class);

    }



    public List<ProductModel> getAllProductsByAdmin() throws ExecutionException, InterruptedException {
        return getProductCollection().orderBy("name").get().get().toObjects(ProductModel.class);

    }

    public ProductModel putProduct(ProductsInfoChange productmodel) throws InterruptedException, ExecutionException {
        ProductModel product = getDocumentById("Products", productmodel.getId()).get().get() .toObject(ProductModel.class);
        List<ProductModel> product2 = getFirebase().collection("Category")
                .whereEqualTo("name", productmodel.getIdcategory()).get().get().toObjects(ProductModel.class);
        product.setIdcategory(product2.get(0).getId());

        product.changeProduct(productmodel);
        getProductDocumentById(productmodel.getId()).set(product);
        return product.changeProduct(productmodel);
    }

    public List<ProductInfoRes> paginateProductOrderByField(int page ,int limit) {
        if (limit == 0) {
            limit=10;
        }


        try {
            DocumentSnapshot start = getProductCollection().orderBy("id").get().get().getDocuments()
                    .get(limit * (page- 1));
            Query coc = getProductCollection().orderBy("id").startAt(start).limit(limit);
            return coc.get().get().toObjects(ProductInfoRes.class);
        } catch (Exception e) {
            return null;
        }

    }

    public List<ProductInfoRes> paginateProductSearchField(PaginateReq page)
            throws ExecutionException, InterruptedException {
        if (page.getLimit() == 0) {
            page.setLimit(10);
        }
        DocumentSnapshot start = getProductCollection().whereGreaterThanOrEqualTo(page.getField(), page.getValue())
                .get().get().getDocuments().get(page.getLimit() * (page.getPage() - 1));
        Query coc = getProductCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return coc.get().get().toObjects(ProductInfoRes.class);

    }

    public ProductModel deleteProduct(String id) throws InterruptedException, ExecutionException {
        ProductModel product;
        product = getDocumentById("Products", id).get().get().toObject(ProductModel.class);
        deleteDocument("Products", id);
        return product;

    }

    public List<ProductInfoRes> getAllProductsByNameCategory(String nameCate) throws ExecutionException, InterruptedException {
        List<CategoryModel> category = getFirebase().collection("Category").whereEqualTo("name", nameCate).get().get().toObjects(CategoryModel.class);
        String id =category.get(0).getId();
        // láy id => lấy product
        List<ProductInfoRes> products = getProductCollection().whereEqualTo("idcategory", id).get().get().toObjects(ProductInfoRes.class);
        return products;

    }



public List<ProductInfoRes> searchProductByName(String value) throws ExecutionException, InterruptedException {
    List<ProductInfoRes> products = getProductCollection().get().get().toObjects(ProductInfoRes.class);
  
    List<ProductInfoRes> myList = new ArrayList<>();

    products.forEach((product) -> {
        if(product.getName().toLowerCase().contains(value.toLowerCase())){
            myList.add(product);
        }
    });
    return myList;
}
}