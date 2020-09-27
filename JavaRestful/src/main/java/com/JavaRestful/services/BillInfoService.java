package com.JavaRestful.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.JavaRestful.models.components.BillInfoModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class BillInfoService extends ServiceBridge{
    
    public CollectionReference getBillInfoCollection (){

        return getFirebase().collection("BillInfo");
    }

    public DocumentReference getBillInfoById(String id )   {
        return getDocumentById("BillInfo",id);
    }

    public List<BillInfoModel> getBillInFoByBillId(String idBill){
        try{
            return getBillInfoCollection().whereEqualTo("idBill",idBill).get().get().toObjects(BillInfoModel.class);
        }catch (Exception e){
            return null;
        }
    }

    public  List<BillInfoModel> getAllBillInfo() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = getBillInfoCollection().get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BillInfoModel> listBillInfo = new ArrayList<>();
        for(QueryDocumentSnapshot doc : documents){
            listBillInfo.add(doc.toObject(BillInfoModel.class));
        }
        return listBillInfo;
    }

    public ArrayList<BillInfoModel> addBillInfo(ArrayList<BillInfoModel> billInfo ) {
        for (int i = 0; i < billInfo.size(); i++) {
           billInfo.get(i).setId(randomDocumentId("BillInfo"));
           billInfo.get(i).setCode(HelpUtility.getRandomCode("BLIF"));
          
           getBillInfoById(billInfo.get(i).getId()).set(billInfo.get(i));
        }
        return billInfo;
    }

    public BillInfoModel deleteBillInfo(String id) throws ExecutionException, InterruptedException {
        BillInfoModel billinfomodel;
        billinfomodel = getDocumentById("Bill",id).get().get().toObject(BillInfoModel.class);
        deleteDocument("BillInfo",id);
        return billinfomodel;
    }
}
