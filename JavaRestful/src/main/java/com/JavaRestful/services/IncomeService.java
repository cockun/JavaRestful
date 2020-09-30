package com.JavaRestful.services;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.components.OutcomeModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.protobuf.Api;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class IncomeService extends ServiceBridge {
    public CollectionReference getIncomeCollection (){

        return getFirebase().collection("InCome");
    }

    public DocumentReference getIncomeDocumentById(String id )   {
        return getDocumentById("InComes",id);
    }

    public IncomeModel addIncome(IncomeModel incomeModel){
        incomeModel.setId(randomDocumentId("InComes"));
        getDocumentById("InComes",incomeModel.getId()).set(incomeModel);
        return incomeModel;
    }

    public ApiResponseData<List<IncomeModel>> getAllInCome() {
        try {
            return new ApiResponseData<>(getIncomeCollection().get().get().toObjects(IncomeModel.class));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

    }

    public List<IncomeModel> paginateIncomeOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        if(page.getField() == "" || page.getField() == null){
            page.setField("id");
        }

        if(page.isOptionSort()){
            try {
                DocumentSnapshot start = getIncomeCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getIncomeCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(IncomeModel.class);
            }catch (Exception e){
                return null;
            }
        }else {
            try {
                DocumentSnapshot start = getIncomeCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getIncomeCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(IncomeModel.class);
            }catch (Exception e){
                return null;
            }


        }

    }

    public List<IncomeModel> paginateIncomeSearchField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        DocumentSnapshot start = getIncomeCollection().whereGreaterThan(page.getField(),page.getValue()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
        Query coc = getIncomeCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return  coc.get().get().toObjects(IncomeModel.class);

    }

    public boolean deleteIncomeByIdBill(String idProduct){
        try {
            IncomeModel incomeModel =  getFirebase().collection("Income").whereEqualTo("idIncome",idProduct).get().get().toObjects(IncomeModel.class).get(0);
            deleteDocument("Income",incomeModel.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
