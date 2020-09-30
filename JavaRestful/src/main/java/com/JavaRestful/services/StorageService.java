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

    public OutcomeModel addOutcome(OutcomeModel outcomeModel){
        outcomeModel.setId(randomDocumentId("Storage"));
        getDocumentById("Storage",outcomeModel.getId()).set(outcomeModel);
        return outcomeModel;
    }

    public ApiResponseData<List<IncomeModel>> getAllStorage() {
        try {
            return new ApiResponseData<>(getStorageCollection().get().get().toObjects(IncomeModel.class));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

    }

    public List<StorageModel> paginateStorageOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        if(page.getField() == "" || page.getField() == null){
            page.setField("id");
        }

        if(page.isOptionSort()){
            DocumentSnapshot start = getStorageCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getStorageCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(StorageModel.class);
        }else {
            DocumentSnapshot start = getStorageCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getStorageCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(StorageModel.class);
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

    public ApiResponseData<StorageModel> addStorage(StorageModel storageModel){

        storageModel.setId(randomDocumentId("Storage"));
        getStorageDocumentById(storageModel.getId()).set(storageModel);

        return null;


    }

}
