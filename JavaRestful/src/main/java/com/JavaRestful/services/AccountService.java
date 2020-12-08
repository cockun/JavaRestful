package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.CustomerTypeModel;
import com.JavaRestful.models.components.RewardPointModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.account.*;
import com.JavaRestful.models.requests.search.SearchReq;
import com.JavaRestful.models.response.account.AccountInfoRes;

import com.google.cloud.firestore.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;



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
            if(findUser(changePassword.getUser()).isEmpty() ){
                return new ApiResponseData<>(false,"Tài khoản không tồn tại");
            }
            if (!HelpUtility.validPassword(changePassword.getPasswordNew())){
                return new ApiResponseData<>(false,"Password phải từ 8 đến 16 ký tự ,có ít nhất 1 ký tự đặc biệt , 1 chữ , 1 số ");
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
            return new ApiResponseData<>(false,e.getMessage());
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
        if(account.getName() == null || account.getUser() == null || account.getPassword() == null || account.getPhone() == null || account.getEmail() == null ){
            return new ApiResponseData<>(false,"Vui lòng điền đủ thông tin ");
        }

        if(!findUser(account.getUser()).isEmpty() ){
            return new ApiResponseData<>(false,"Tài khoản đã tồn tại");
        }
        if (!HelpUtility.validPassword(account.getPassword())){
            return new ApiResponseData<>(false,"Password phải từ 8 đến 16 ký tự ,có ít nhất 1 ký tự đặc biệt , 1 chữ , 1 số ");
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
            account.setIdCustomer("pteVs6y3PzjbdlGBJF2l");
            account.setPassword(encryptPassword(account.getPassword()));

            RewardPointModel rewardPointModel = new RewardPointModel();
            rewardPointModel.setIdAccount(account.getId());
            rewardPointModel.setPointAvailable(0);
            rewardPointModel.setPointRank(0);

            getFirebase().collection("Accounts").document(account.getId()).collection("RewardPoint").add(rewardPointModel);

            getAccountDocumentById(account.getId()).set(account);
            AccountInfoRes accountInfoRes = new AccountInfoRes(account);
            accountInfoRes.setType("Thường");
            return  new ApiResponseData<>(accountInfoRes ) ;

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
            if(!HelpUtility.validPhone(accountInfoChange.getPhone())){
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


    public List<AccountInfoRes> searchAccount(String field , String value) throws ExecutionException, InterruptedException {
        List<AccountModel> accountModelList = getAccountCollection().orderBy("name").get().get().toObjects(AccountModel.class);
        List<AccountInfoRes> myList = new ArrayList<>();
        if(!accountModelList.isEmpty()){
            switch (field){
                case "name":
                    for(AccountModel accountModel : accountModelList){
                        if(accountModel.getName().toLowerCase().contains(value.toLowerCase())){
                            AccountInfoRes accountInfoRes = new AccountInfoRes(accountModel);
                            try {
                                accountInfoRes.setRewardPoint(getPoint(accountModel));
                                accountInfoRes.setType(getTypeCustomer(accountModel));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            myList.add(accountInfoRes);
                        }
                    }
                    break;
                case "user": 
                    for(AccountModel accountModel : accountModelList){
                        if(accountModel.getUser().toLowerCase().contains(value.toLowerCase())){
                            AccountInfoRes accountInfoRes = new AccountInfoRes(accountModel);
                            try {
                                accountInfoRes.setRewardPoint(getPoint(accountModel));
                                accountInfoRes.setType(getTypeCustomer(accountModel));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            myList.add(accountInfoRes);
                        }
                    }
                    break;
                default:break;

            }
            return myList;
        }
        return null;

    }
    public ApiResponseData<List<AccountInfoRes>> paginateAccount(PaginateAccountReq paginateAccountReq )
            throws ExecutionException, InterruptedException {
        if (paginateAccountReq.getPage() < 1) {
            return new ApiResponseData<>(false,"page phải từ 1");
        }
        if (paginateAccountReq.getLimit() < 1) {
            return new ApiResponseData<>(false, "limit phải từ 1 ");
        }
        List<AccountInfoRes> list  = searchAccount(paginateAccountReq.getField(),paginateAccountReq.getValue());
        try {
            list = list.subList((paginateAccountReq.getPage() - 1) * paginateAccountReq.getLimit(), paginateAccountReq.getLimit());

        } catch (Exception e) {
            
        }

        return new ApiResponseData<>(list)  ;

    }









    public String getTypeCustomer(AccountModel accountModel) throws ExecutionException, InterruptedException {
        CustomerTypeModel customer = getFirebase().collection("Customer").document(accountModel.getIdCustomer()).get().get().toObject(CustomerTypeModel.class);
        if (customer != null) {
            return customer.getTypeCustomer();
        }
        return "Thường";
    }

    public float getPoint(AccountModel accountModel) throws ExecutionException, InterruptedException {
        RewardPointModel rewardPoint = getFirebase().collectionGroup("RewardPoint").whereEqualTo("idAccount",accountModel.getId()).get().get().toObjects(RewardPointModel.class).get(0);
        if(rewardPoint == null){
            return 0;
        }
        return rewardPoint.getPointAvailable();
    }   

    

    





}
