package com.JavaRestful.controllers;


import com.JavaRestful.models.ProductModel;
import com.JavaRestful.services.ProductService;
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
public class ProductController {
    @Autowired
    FirebaseInitializer db;

    @GetMapping("/product")
    public List<String> getProduct() throws ExecutionException, InterruptedException {

        List<String> accounts = new ArrayList<String>();

        ApiFuture<QuerySnapshot> querySnapshot  = db.getFirebase().collection("Products").get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
                accounts.add(doc.getId());
        }
        db.getFirebase().collection("Products").add(new ProductModel("nghi", 2000,2005, "dead", "dead", 200, "dead"));

        return accounts ;

    }
    
}
