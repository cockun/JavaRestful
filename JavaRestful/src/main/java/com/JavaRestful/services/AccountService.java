package com.JavaRestful.services;

import com.JavaRestful.models.AccountModel;
import com.google.cloud.firestore.DocumentReference;

import java.util.concurrent.ExecutionException;


public class AccountService extends ServiceBridge  {


    public AccountModel addAccount(AccountModel account){
        account.setId(randomDocumentId("Accounts"));
        getFirebase().collection("Accounts").add(account);

        return account;
    }


    public  AccountModel addAccount(String user , String password , String name , String phone, String address , boolean author){
        AccountModel account = new AccountModel(user,password,name,phone,address,author   );

        return addAccount(account);
    }


    public  AccountModel getAccountById(String id) throws InterruptedException, ExecutionException {

        AccountModel accountModel =  getDocumentById("Accounts",id).toObject(AccountModel.class);
        return accountModel;

    }
    public AccountModel putAccount(AccountModel accountModel){
        return  new AccountModel();
    }

}
