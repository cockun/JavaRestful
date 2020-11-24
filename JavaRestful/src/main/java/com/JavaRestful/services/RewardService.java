package com.JavaRestful.services;


import com.JavaRestful.models.components.AccountModel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RewardService extends ServiceBridge {

    public CollectionReference getAccountCollection (){

        return getFirebase().collection("RewardPoint");
    }

    public DocumentReference getRewardDocumentById(String id )   {
        return getDocumentById("RewardPoint",id);
    }


    public  AccountModel getRewardByIdUser(String user) throws ExecutionException, InterruptedException {
        return getAccountCollection().whereEqualTo("idUser",user).get().get().toObjects((AccountModel.class)).get(0);
    }





}
