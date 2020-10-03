package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.account.AccountInfoChange;
import com.JavaRestful.models.requests.account.ChangeAuthor;
import com.JavaRestful.models.requests.account.RegisterByUserReq;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;

import com.google.cloud.firestore.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

    public List<AccountModel> checkUser(String user){
        try{
            return getAccountCollection().whereEqualTo("user",user).get().get().toObjects(AccountModel.class);
        }catch (Exception e){
            return null;
        }
    }

    public  AccountModel getAccountDocumentByUser(String user) {
        try {
            return getAccountCollection().whereEqualTo("user",user).get().get().toObjects((AccountModel.class)).get(0);
        }catch (Exception  e){
            return null;
        }
    }



    /*------------------------------------------------------------------------------*/


    public ApiResponseData<AccountInfoRes>  login (Login login ) {
        try {
            AccountModel accountModel = getAccountDocumentByUser(login.getUser());
            if((accountModel.getPassword()).equals(encryptPassword(login.getPassword()))){
                return new  ApiResponseData<>(new AccountInfoRes(accountModel) );
            }else {
                return  new  ApiResponseData<>(false,"Sai tên tài khoản hoặc mật khẩu");
            }
        }catch ( Exception e){
            return new ApiResponseData<>(false,"Sai tên tài khoản hoặc mật khẩu");
        }

    }

    public List<AccountInfoRes> paginateAccountOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }

        if(page.getField() == "" || page.getField() == null){
            page.setField("id");
        }


        if(page.isOptionSort()){
            try {
                DocumentSnapshot start = getAccountCollection().orderBy(page.getField()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getAccountCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(AccountInfoRes.class);
            }catch (Exception e){
                return null;
            }
        }else {
            try {
                DocumentSnapshot start = getAccountCollection().orderBy(page.getField(), Query.Direction.DESCENDING).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
                Query coc = getAccountCollection().orderBy(page.getField(), Query.Direction.DESCENDING).startAt(start).limit(page.getLimit());
                return  coc.get().get().toObjects(AccountInfoRes.class);
            }catch (Exception e){
                return null;
            }


        }

    }

    public List<AccountInfoRes> paginateAccountSearchField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }
        DocumentSnapshot start = getAccountCollection().whereGreaterThan(page.getField(),page.getValue()).get().get().getDocuments().get(page.getLimit()*(page.getPage()-1));
        Query coc = getAccountCollection().orderBy(page.getField()).startAt(start).limit(page.getLimit());
        return  coc.get().get().toObjects(AccountInfoRes.class);

    }


    public ApiResponseData<AccountInfoRes>  addAccountByAdmin(AccountModel account )
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(account.getPassword().length() <8){
            return new ApiResponseData<>(false,"Password phải lớn hơn 8 ký tự");
        }
        if( !checkUser(account.getUser()).isEmpty() ){
            return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
        }
        if(account.getName() == null || account.getUser() == null || account.getPassword() == null ){
            return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
        }

            account.setId(randomDocumentId("Accounts"));

            account.setPassword(encryptPassword(account.getPassword()));
            getAccountDocumentById(account.getId()).set(account);
            
            return  new ApiResponseData<>(new AccountInfoRes(account) ) ;

    }

    public ApiResponseData<AccountInfoRes>  addAccountByUser(RegisterByUserReq registerByUserReq ) {

        try{
            if(!checkUser(registerByUserReq.getUser()).isEmpty() ){
                return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
            }
            if(registerByUserReq.getName() == null || registerByUserReq.getUser() == null || registerByUserReq.getPassword() == null ){
                return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
            }

            AccountModel accountModel  = new AccountModel(registerByUserReq);

            accountModel.setId(randomDocumentId("Accounts"));

            accountModel.setPassword(encryptPassword(accountModel.getPassword()));
            getAccountDocumentById(accountModel.getId()).set(accountModel);

            return  new ApiResponseData<>(new AccountInfoRes(accountModel) ) ;
        }catch (Exception e){
            return  null    ;
        }



    }





    public  AccountModel getAccountById(String id) throws InterruptedException, ExecutionException {

        return  getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);

    }


    public AccountModel putAccount(AccountInfoChange accountInfoChange)  {
        try{
            AccountModel accountModel = getAccountDocumentById(accountInfoChange.getId()).get().get().toObject(AccountModel.class);
            accountModel.changeData(accountInfoChange);
            getAccountDocumentById(accountInfoChange.getId()).set(accountModel);
            return accountModel.changeData(accountInfoChange);
        }catch (Exception e){
            return null;
        }

    }
    // public AccountModel putAccountAdmin(AccountModel account)  {
    //     try{
    //         AccountModel accountModel = getAccountDocumentById(account.getId()).get().get().toObject(AccountModel.class);
    //         getAccountById(accountModel.getId()).set(account);
            
    //     }catch (Exception e){
    //         return null;
    //     }

    // }


    public ApiResponseData<String> putAuthor (ChangeAuthor changeAuthor)  {
        try {
            AccountModel accountModel = getAccountDocumentById(changeAuthor.getId()).get().get().toObject(AccountModel.class);
            accountModel.setAuthor(changeAuthor.isAuthor());
            getAccountDocumentById(changeAuthor.getId()).set(accountModel);
            return new ApiResponseData<>("Thành công");
        }catch (Exception e){
            return new ApiResponseData<>(false,"id không tồn tại");
        }

    }

    public AccountModel deleteAccount(String id) throws ExecutionException, InterruptedException {
        AccountModel accountModel;
        accountModel = getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);
        deleteDocument("Accounts",id);
        return accountModel;
    }


    public  List<AccountInfoRes> getAllAccounts() throws ExecutionException, InterruptedException {

        return getAccountCollection().orderBy("name").get().get().toObjects(AccountInfoRes.class);
    }


    public static String encryptPassword(String text) throws NoSuchAlgorithmException,UnsupportedEncodingException
    {
        String text1;
        MessageDigest msd = MessageDigest.getInstance("MD5");
        byte[] textByte = text.getBytes("UTF-8");
        byte[] text1Byte = msd.digest(textByte);
        BigInteger bigInt = new BigInteger(1,text1Byte);
         text1 = bigInt.toString(16);
         return text1;
    }




}
