package com.JavaRestful.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.ProductModel;
import com.JavaRestful.models.components.SupplierModel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

public class SupplierService extends ServiceBridge {

    public CollectionReference getSupplierCollection() {

        return getFirebase().collection("Supplier");
    }

    public DocumentReference getSupplierDocumentById(String id) {
        return getDocumentById("Supplier", id);
    }

    public List<SupplierModel> getAllSupplier() throws ExecutionException, InterruptedException {

        return getSupplierCollection().get().get().toObjects((SupplierModel.class));

    }

    public SupplierModel getSupplierById(String id) throws ExecutionException, InterruptedException {
        return getSupplierDocumentById(id).get().get().toObject(SupplierModel.class);
    }

    public SupplierModel getSupplierByIdProduct(String idProduct) throws ExecutionException, InterruptedException {

        ProductModel productModel = getFirebase().collection("Products").document(idProduct).get().get()
                .toObject(ProductModel.class);
        return getSupplierCollection().whereEqualTo("id", productModel.getIdcategory()).get().get()
                .toObjects(SupplierModel.class).get(0);
    }

    public ApiResponseData<SupplierModel> addSupplier(SupplierModel supplierModel) {
        try {
            supplierModel.setId(randomDocumentId("Supplier"));
            getSupplierDocumentById(supplierModel.getId()).set(supplierModel);
            return new ApiResponseData<>(supplierModel);
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
    }

    public ApiResponseData<SupplierModel> putSupplier (SupplierModel supplierModel){
        try {
            getSupplierDocumentById(supplierModel.getId()).set(supplierModel);
            return new ApiResponseData<>(supplierModel);
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
    }

    public ApiResponseData<String> deleteSupplier(String id ){
        try {
            deleteDocument("Supplier", id);
            return new ApiResponseData<>("success");
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
        
       
    }

}
