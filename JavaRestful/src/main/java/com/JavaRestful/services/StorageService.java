package com.JavaRestful.services;

import com.JavaRestful.models.components.*;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.outcome.InputStorageReq;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StorageService extends ServiceBridge{

    public CollectionReference getStorageCollection (){

        return getFirebase().collection("Storage");
    }


    public DocumentReference getStorageDocumentById(String id )   {
        return getDocumentById("Storage",id);
    }

    public List<StorageModel> getStorageByIdProduct(String id ) throws ExecutionException, InterruptedException {
        return getStorageCollection().whereEqualTo("idProduct",id).get().get().toObjects(StorageModel.class);
    }





    public OutcomeModel addOutcome(OutcomeModel outcomeModel){
        outcomeModel.setId(randomDocumentId("Storage"));
        getDocumentById("Storage",outcomeModel.getId()).set(outcomeModel);
        return outcomeModel;
    }

    public ApiResponseData<List<StorageModel>> getAllStorage() throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(getStorageCollection().get().get().toObjects(StorageModel.class));

    }

    public List<StorageModel> paginateStorageOrderByField(PaginateReq page)  {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        if(page.getField().equals("")|| page.getField() == null){
            page.setField("id");
        }

        if(page.isOptionSort()){
            try {
                DocumentSnapshot start = getStorageCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getStorageCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(StorageModel.class);
            }catch (Exception e){
                return null;
            }
        }else {
            try {
                DocumentSnapshot start = getStorageCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getStorageCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(StorageModel.class);
            }catch (Exception e){
                return null;
            }


        }

    }

    public List<StorageModel> paginateStorageSearchField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        DocumentSnapshot start = getStorageCollection().whereGreaterThan(page.getField(),page.getValue()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
        Query coc = getStorageCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return  coc.get().get().toObjects(StorageModel.class);

    }

    public ApiResponseData<StorageModel> addStorage(InputStorageReq inputStorageReq) {
        try{
            StorageModel storageModel ;

            List<StorageModel> storageModelList = getStorageByIdProduct(inputStorageReq.getIdProduct());
            if(storageModelList.isEmpty()){
                storageModel = new StorageModel(inputStorageReq);
                storageModel.setId(randomDocumentId("Storage"));
                getStorageDocumentById(storageModel.getId()).set(storageModel);
            }else{

                storageModelList.get(0).setQuantity(storageModelList.get(0).getQuantity()+inputStorageReq.getQuantity());
                getStorageDocumentById(storageModelList.get(0).getId()).set(storageModelList.get(0));
                storageModel =  storageModelList.get(0);
            }


            //outcome
            OutcomeService outcomeService = new OutcomeService();
            OutcomeModel outcomeModel = new OutcomeModel();
            outcomeModel.setIdOutcome(inputStorageReq.getIdProduct());
            outcomeModel.setDate(java.time.LocalDate.now().toString());
            outcomeModel.setQuantity(inputStorageReq.getQuantity());

            ProductService productService = new ProductService();

            ProductModel productModel =  productService.getProductByIdAdmin(inputStorageReq.getIdProduct());

            outcomeModel.setCost(productModel.getRootprice()*inputStorageReq.getQuantity());
            outcomeModel.setNote(inputStorageReq.getNote());

            outcomeService.addOutcome(outcomeModel);

            return new ApiResponseData<>(storageModel);

        }catch (Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

    }



}
