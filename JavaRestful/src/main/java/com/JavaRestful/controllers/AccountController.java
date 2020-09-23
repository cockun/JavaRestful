package com.JavaRestful.controllers;

import com.JavaRestful.models.AccountModel;
import com.JavaRestful.services.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class AccountController {
    @Autowired
    FirebaseInitializer db;


    @GetMapping("/account")
    public List<AccountModel> getAccount() throws ExecutionException, InterruptedException {

        List<AccountModel> accounts = new ArrayList<AccountModel>();
        CollectionReference account = db.getFirebase().collection("Accounts");
        ApiFuture<QuerySnapshot> querySnapshot  = account.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
                accounts.add(doc.toObject(AccountModel.class));
        }
        return accounts;

    }
}
