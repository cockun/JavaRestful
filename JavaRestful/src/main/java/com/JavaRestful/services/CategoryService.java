package com.JavaRestful.services;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.models.components.CategoryModel;
import com.JavaRestful.models.components.ProductModel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryService extends ServiceBridge {

    public CollectionReference getCategoryCollection() {

        return getFirebase().collection("Category");
    }

    public DocumentReference getCategoryDocumentById(String id) {
        return getDocumentById("Category", id);
    }

    public List<CategoryModel> getAllCategories() throws ExecutionException, InterruptedException {

        return getCategoryCollection().get().get().toObjects((CategoryModel.class));

    }

    public CategoryModel getCategoryById(String id) throws ExecutionException, InterruptedException {
        return getCategoryDocumentById(id).get().get().toObject(CategoryModel.class);
    }

    public CategoryModel getCategoryByIdProduct(String idProduct) throws ExecutionException, InterruptedException {

        ProductModel productModel = getFirebase().collection("Products").document(idProduct).get().get()
                .toObject(ProductModel.class);
        return getCategoryCollection().whereEqualTo("id", productModel.getIdcategory()).get().get()
                .toObjects(CategoryModel.class).get(0);
    }

    public ApiResponseData<CategoryModel> addCategory(CategoryModel categoryModel) {
        try {
            categoryModel.setId(randomDocumentId("Category"));
            getCategoryDocumentById(categoryModel.getId()).set(categoryModel);
            return new ApiResponseData<>(categoryModel);
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
    }

    public ApiResponseData<CategoryModel> putCategory(CategoryModel categoryModel) {
        try {
            getCategoryDocumentById(categoryModel.getId()).set(categoryModel);
            return new ApiResponseData<>(categoryModel);
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
    }

    public ApiResponseData<String> deleteCategory(String id) {
        try {
            deleteDocument("Category", id);
            return new ApiResponseData<>("success");
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }

    }

}
