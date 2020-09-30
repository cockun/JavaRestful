package com.JavaRestful.services;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.components.OutcomeModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OutcomeService extends ServiceBridge{

    public CollectionReference getOutcomeCollection (){

        return getFirebase().collection("Outcome");
    }


    public DocumentReference getOutcomeDocumentById(String id )   {
        return getDocumentById("Outcome",id);
    }

    public OutcomeModel addOutcome(OutcomeModel outcomeModel){
        outcomeModel.setId(randomDocumentId("Outcome"));
        getDocumentById("Outcome",outcomeModel.getId()).set(outcomeModel);
        return outcomeModel;
    }

    public ApiResponseData<List<IncomeModel>> getAllOutCome() {
        try {
            return new ApiResponseData<>(getOutcomeCollection().get().get().toObjects(IncomeModel.class));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

    }

    public List<OutcomeModel> paginateOutcomeOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        if(page.getField() == "" || page.getField() == null){
            page.setField("id");
        }

        if(page.isOptionSort()){
            DocumentSnapshot start = getOutcomeCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getOutcomeCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(OutcomeModel.class);
        }else {
            DocumentSnapshot start = getOutcomeCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getOutcomeCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(OutcomeModel.class);
        }

    }

    public List<OutcomeModel> paginateOutcomeSearchField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        DocumentSnapshot start = getOutcomeCollection().whereGreaterThan(page.getField(),page.getValue()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
        Query coc = getOutcomeCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return  coc.get().get().toObjects(OutcomeModel.class);

    }








}
