package com.JavaRestful.controllers;


import com.JavaRestful.services.AccountService;
import com.JavaRestful.services.FirebaseInitializer;
import com.google.api.core.ApiFuture;
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
    public List<String> getAccount() throws ExecutionException, InterruptedException {

        List<String> accounts = new ArrayList<String>();

        ApiFuture<QuerySnapshot> querySnapshot  = db.getFirebase().collection("Accounts").get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
                accounts.add(doc.getId());
        }
        db.getFirebase().collection("Accounts").add(AccountService.addAllData("coc","123","fdjfhdjkhfdjkh","32938298",true));

        return accounts ;

    }
}
