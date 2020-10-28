package com.JavaRestful.services;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;


@Service
public class ServiceBridge {
    @PostConstruct
    private void initDb() throws IOException {
        InputStream serviceAccount =
                this.getClass().getClassLoader().getResourceAsStream("./serviceaccount.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://javarestful-8e0e3.firebaseio.com")
                .build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }else{
            System.out.print("connect fail");
        }

    }




    public Firestore getFirebase(){
        return FirestoreClient.getFirestore();
    }

    public DocumentReference getDocumentById(String collection , String id  ) {

        return this.getFirebase().collection(collection).document(id);
    }

    public  String randomDocumentId(String collection){
        return this.getFirebase().collection(collection).document().getId();
    }
    public boolean deleteDocument(String collection , String id ) {
        try{
            getFirebase().collection(collection).document(id).delete();
            return true;
        }catch (Exception e){
            return false;
        }
    }



























}
