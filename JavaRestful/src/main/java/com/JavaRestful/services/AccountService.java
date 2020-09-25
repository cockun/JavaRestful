package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class AccountService extends ServiceBridge  {

    public CollectionReference getAccountCollection (){

        return getFirebase().collection("Accounts");
    }

    public AccountModel addAccount(AccountModel account ){
        account.setId(randomDocumentId("Accounts"));
       getAccountCollection().document(account.getId()).set(account);
        return account;
    }

    public  AccountModel addAccount(String user , String password , String name , String phone, String address , boolean author){
        AccountModel account = new AccountModel(user,password,name,phone,address,author   );

        return addAccount(account);
    }

    public  AccountModel getAccountById(String id) throws InterruptedException, ExecutionException {



        return  getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);

    }

    public AccountModel putAccount(AccountModel accountModel)  {
        getAccountCollection().document(accountModel.getId()).set(accountModel);
        return accountModel;
    }
    // public AccountModel putAccount(String id ,String user,String pass,String name, String phone,String address ) throws ExecutionException, InterruptedException
    // {
    //     AccountModel accountModel;
    //     accountModel = getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);
    //     accountModel.setUser(user);
    //     accountModel.setAddress(address);
    //     accountModel.setAuthor(true);
    //     accountModel.setPassword(pass);
    //     accountModel.setPhone(phone);
    //     return accountModel;
    // }
    public AccountModel deleteAccount(String id) throws ExecutionException, InterruptedException {
        AccountModel accountModel;
        accountModel = getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);
        deleteDocument("Accounts",id);
        return accountModel;
    }

    public  List<AccountModel> getAllAccounts() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = getAccountCollection().get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<AccountModel> listAccounts = new ArrayList<>();
        for(QueryDocumentSnapshot doc : documents){
            listAccounts.add(doc.toObject(AccountModel.class));
        }

        return listAccounts;

    }



}
