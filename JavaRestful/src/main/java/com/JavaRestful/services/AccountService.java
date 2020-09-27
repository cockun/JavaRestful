package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class AccountService extends ServiceBridge  {




    public CollectionReference getAccountCollection (){

        return getFirebase().collection("Accounts");
    }

    public DocumentReference getAccountDocumentById(String id )   {
        return getDocumentById("Accounts",id);
    }

    public List<AccountModel> findUser(String user){
        try{
            return getAccountCollection().whereGreaterThan("user",user).get().get().toObjects(AccountModel.class);
        }catch (Exception e){
            return null;
        }
    }

    public  AccountModel getAccountDocumentByUser(String user) {
        try {
            AccountModel accountModel = getAccountCollection().whereEqualTo("user",user).get().get().toObjects((AccountModel.class)).get(0);
            return accountModel;
        }catch (Exception  e){
            return null;
        }
    }



    /*------------------------------------------------------------------------------*/


    public ApiResponseData<AccountInfoRes>  login (Login login ) {
        try {
            AccountModel accountModel = getAccountDocumentByUser(login.getUser());
            if(accountModel.getPassword().equals(login.getPassword())){
                return new  ApiResponseData<>(new AccountInfoRes(accountModel) );
            }else {
                return  new  ApiResponseData<>(false,"Sai tên tài khoản hoặc mật khẩu");
            }
        }catch ( Exception e){
            return new ApiResponseData<>(false,"Sai tên tài khoản hoặc mật khẩu");
        }

    }




    public List<AccountInfoRes> paginateAccountOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {

        if(page.isOption()){
            DocumentSnapshot start = getAccountCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getAccountCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(AccountInfoRes.class);
        }else {
            DocumentSnapshot start = getAccountCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
            Query coc = getAccountCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
            return  coc.get().get().toObjects(AccountInfoRes.class);
        }

    }

    public ApiResponseData<AccountInfoRes>  addAccount(AccountModel account ) {

        if(account.getName() == null || account.getUser() == null || account.getPassword() == null || !findUser(account.getUser()).isEmpty() ){
            return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
        }
        if(account.getName() == null || account.getUser() == null || account.getPassword() == null ){
            return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
        }

            account.setId(randomDocumentId("Accounts"));
            getAccountDocumentById(account.getId()).set(account);
            return  new ApiResponseData<>(new AccountInfoRes(account) ) ;

    }

    public  AccountModel getAccountById(String id) throws InterruptedException, ExecutionException {

        return  getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);

    }

    public AccountModel putAccount(AccountModel accountModel)  {
        getAccountCollection().document(accountModel.getId()).set(accountModel);
        return accountModel;
    }

    public AccountModel deleteAccount(String id) throws ExecutionException, InterruptedException {
        AccountModel accountModel;
        accountModel = getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);
        deleteDocument("Accounts",id);
        return accountModel;
    }


    public  List<AccountInfoRes> getAllAccounts() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = getAccountCollection().get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<AccountInfoRes> listAccounts = new ArrayList<>();
        for(QueryDocumentSnapshot doc : documents){
            listAccounts.add(doc.toObject(AccountInfoRes.class));
        }

        return listAccounts;

    }




}
