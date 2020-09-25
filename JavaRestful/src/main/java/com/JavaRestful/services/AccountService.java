package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;

import java.util.concurrent.ExecutionException;


public class AccountService extends ServiceBridge  {


    public AccountModel addAccount(AccountModel account){
        account.setId(randomDocumentId("Accounts"));
        getFirebase().collection("Accounts").document(account.getId()).set(account);
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
        getFirebase().collection("Accounts").document(accountModel.getId()).set(accountModel);
        return accountModel;
    }
    public AccountModel deleteAccount(String id) throws ExecutionException, InterruptedException {
        AccountModel accountModel;
        accountModel = getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);
        deleteDocument("Accounts",id);
        return accountModel;
    }


}
