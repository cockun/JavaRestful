package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class AccountService extends ServiceBridge  {

    public CollectionReference getAccountCollection (){

        return getFirebase().collection("Accounts");
    }

    public DocumentReference getAccountDocumentById(String id )   {
        return getDocumentById("Accounts",id);
    }




    public AccountModel getAccountDocumentByUser(String user) {
        try {
            List<AccountModel> accountModelList = getAccountCollection().whereEqualTo("user",user).get().get().toObjects((AccountModel.class));
            if(accountModelList.isEmpty()){
                return null;
            }else {
                return accountModelList.get(0);
            }
        }catch (Exception  e){
            return null;
        }


    }

    public List<AccountModel> findUser(String user){

        try{
           return getAccountCollection().whereEqualTo("user",user).get().get().toObjects(AccountModel.class);
        }catch (Exception e){
            return null;
        }

    }

    public AccountModel login (Login login ) {
        try {
            AccountModel accountModel = getAccountDocumentByUser(login.getUser());
            if(accountModel.getPassword().equals(login.getPassword())){
                return accountModel;
            }else {
                return null;
            }
        }catch ( Exception e){
            return null;
        }

    }




    public AccountModel addAccount(AccountModel account ) {

        if(account.getName() == null || account.getUser() == null || account.getPassword() == null || !findUser(account.getUser()).isEmpty() ){
            return null;
        }else {
            account.setId(randomDocumentId("Accounts"));
            getAccountDocumentById(account.getId()).set(account);
            return account;
        }


    }

    public  AccountModel addAccount(String user , String password , String name , String phone, String address , boolean author) throws ExecutionException, InterruptedException {
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


    public  List<AccountInfoRes> getAllAccounts() throws ExecutionException, InterruptedException {

        return getAccountCollection().orderBy("name").get().get().toObjects(AccountInfoRes.class);

    }



}
