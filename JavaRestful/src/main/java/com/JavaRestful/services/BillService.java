package com.JavaRestful.services;

import com.JavaRestful.models.components.BillModel;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import com.google.api.core.ApiFuture;
import com.google.cloud.Date;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.JavaRestful.services.HelpUtility;

public class BillService extends ServiceBridge {
    public CollectionReference getBillCollection (){

        return getFirebase().collection("Bill");
    }

    public DocumentReference getBillDocumentById(String id )   {
        return getDocumentById("Bill",id);
    }

    public List<BillModel> getBillDocumentByUser(String user) {
        try {
            List<BillModel> billModelList = getBillCollection().whereEqualTo("nameUser",user).get().get().toObjects((BillModel.class));
            if(billModelList.isEmpty()){
                return null;
            }else {
                return billModelList;
            }
        }catch (Exception  e){
            return null;
        }
    }

    public BillModel addBill(BillModel bill ) {
        bill.setId(randomDocumentId("Bill"));
        bill.setCode(HelpUtility.getRandomCode("BL"));
        bill.setDate(java.time.LocalDate.now().toString());
        getBillDocumentById(bill.getId()).set(bill);
        return bill;
    }



    public BillModel deleteBill(String id) throws ExecutionException, InterruptedException {
        BillModel billmodel;
        billmodel = getDocumentById("Bill",id).get().get().toObject(BillModel.class);
        deleteDocument("Bill",id);
        return billmodel;
    }

    public  List<BillModel> getAllBill() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = getBillCollection().get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BillModel> listBill = new ArrayList<>();
        for(QueryDocumentSnapshot doc : documents){
            listBill.add(doc.toObject(BillModel.class));
        }

        return listBill;

    }
}
