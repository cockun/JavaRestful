package com.JavaRestful.services;

import com.JavaRestful.models.components.BillModel;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.DocumentReference;

@Service
public class BillService extends ServiceBridge {
    public BillModel addBill(BillModel bill)
    {
        bill.setId(randomDocumentId("Bill"));
        getFirebase().collection("Products").add(bill);
        return bill; 
    }

    public  BillModel addBill(double total, String date , String idUser){
        BillModel  newBill = new BillModel(total , date , idUser);
        return addBill(newBill);
    }
}
