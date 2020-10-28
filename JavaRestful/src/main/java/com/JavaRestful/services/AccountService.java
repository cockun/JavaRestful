package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.account.*;
import com.JavaRestful.models.response.account.AccountInfoRes;

import com.JavaRestful.models.response.account.ProductInfoRes;
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
            return getAccountCollection().whereEqualTo("user",user).get().get().toObjects(AccountModel.class);
        }catch (Exception e){
            return null;
        }
    }

    public List<AccountModel> findEmail(String email){
        try{
            return getAccountCollection().whereEqualTo("email",email).get().get().toObjects(AccountModel.class);
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

    public ApiResponseData<String> changePassword (ChangePassword changePassword ) {
        try {
            if( changePassword.getUser() == null || changePassword.getPassword() == null || changePassword.getPasswordNew() == null){
                return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
            }
            if(!findUser(changePassword.getUser()).isEmpty() ){
                return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
            }
            if (!HelpUtility.validPassword(changePassword.getPasswordNew())){
                return new ApiResponseData<>(false,"Password phải từ 8 đến 16 ký tự ,có ít nhất 1 ký tự đặc biết , 1 chữ thường, 1 chữ in hoa, 1 số ");
            }

            AccountModel accountModel = getAccountDocumentByUser(changePassword.getUser());
            if((accountModel.getPassword()).equals(encryptPassword(changePassword.getPassword()))){
                accountModel.setPassword(encryptPassword(changePassword.getPasswordNew()));
                getAccountDocumentById(accountModel.getId()).set(accountModel);
                return new ApiResponseData<>("Thành công");
            }else {
                return  new  ApiResponseData<>(false,"Sai password");
            }
        }catch ( Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

    }

    public List<AccountInfoRes> paginateAccountOrderByField(PaginateReq page) throws ExecutionException, InterruptedException {
        if(page.getLimit() == 0 ){
            page.setLimit(10);
        }

        if(page.getField().equals("")  || page.getField() == null){
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


    public ApiResponseData<AccountInfoRes>  addAccount(AccountModel account )
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if(account.getName() == null || account.getUser() == null || account.getPhone() == null || account.getPassword() == null || account.getPhone() == null || account.getEmail() == null ){
            return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
        }

        if(!findUser(account.getUser()).isEmpty() ){
            return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
        }
        if (!HelpUtility.validPassword(account.getPassword())){
            return new ApiResponseData<>(false,"Password phải từ 8 đến 16 ký tự ,có ít nhất 1 ký tự đặc biết , 1 chữ thường, 1 chữ in hoa, 1 số ");
        }
        if(!HelpUtility.validEmail(account.getEmail())){
            return new ApiResponseData<>(false,"Email không hợp lệ");
        }
        if(!HelpUtility.validPhone(account.getPhone())){
            return new ApiResponseData<>(false,"Số điện thoại không hợp lệ");
        }
        if(!findEmail(account.getEmail()).isEmpty() ){
            return new ApiResponseData<>(false,"Email đã được đăng ký");
        }



            account.setId(randomDocumentId("Accounts"));

            account.setPassword(encryptPassword(account.getPassword()));
            getAccountDocumentById(account.getId()).set(account);

            return  new ApiResponseData<>(new AccountInfoRes(account) ) ;

    }

    public List<ProductInfoRes> paginateAccount(int page , int limit) {
        if (limit == 0) {
            limit=10;
        }
        try {
            DocumentSnapshot start = getAccountCollection().orderBy("id").get().get().getDocuments()
                    .get(limit * (page- 1));
            Query coc = getAccountCollection().orderBy("id").startAt(start).limit(limit);
            return coc.get().get().toObjects(ProductInfoRes.class);
        } catch (Exception e) {
            return null;
        }

    }



    public  AccountModel getAccountById(String id) throws InterruptedException, ExecutionException {

        return  getDocumentById("Accounts",id).get().get().toObject(AccountModel.class);

    }


    public ApiResponseData<AccountInfoRes>  putAccount(AccountInfoChange accountInfoChange)  {
        try{
            if(accountInfoChange.getName() == null || accountInfoChange.getPhone() == null  || accountInfoChange.getPhone() == null || accountInfoChange.getEmail() == null ){
                return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
            }
            if(!HelpUtility.validEmail(accountInfoChange.getEmail())){
                return new ApiResponseData<>(false,"Email không hợp lệ");
            }
            if(!HelpUtility.validEmail(accountInfoChange.getPhone())){
                return new ApiResponseData<>(false,"Số điện thoại không hợp lệ");
            }
            AccountModel accountModel = getAccountDocumentById(accountInfoChange.getId()).get().get().toObject(AccountModel.class);
            accountModel.changeData(accountInfoChange);
            getAccountDocumentById(accountInfoChange.getId()).set(accountModel);
            return  new ApiResponseData<>(new AccountInfoRes(accountModel));
        }catch (Exception e){
            return null;
        }

    }


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
