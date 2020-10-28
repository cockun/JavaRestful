package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.components.ApiResponseData;

import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.account.*;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.JavaRestful.services.AccountService;

import com.google.protobuf.Api;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController

public class AccountController extends ControllerBridge {
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    @GetMapping("/login")
    public  ApiResponseData<AccountInfoRes> login(@RequestParam String user,  @RequestParam String password) {

        return this.accountService.login(new Login(user,password));
        // return token
    }

    @GetMapping("/account")
    public ApiResponseData<AccountInfoRes> getAccount(@RequestParam String id) {

        // add author
        try {
            return new ApiResponseData<>(new AccountInfoRes(this.accountService.getAccountById(id)));

        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @PutMapping("/account")
    public ApiResponseData<AccountInfoRes> putAccount(@RequestBody AccountInfoChange accountInfoChange) {
        try {

            return this.accountService.putAccount(accountInfoChange);
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Kiểm tra lại thông tin ");
        }
    }
    // @PutMapping("/admin/account")
    // public ApiResponseData<AccountModel> putAccountAdmin(@RequestBody AccountModel account) {
    //     try {
    //         // add author
    //         return new ApiResponseData<>(new AccountModel(this.accountService.putAccount(account)));
    //     } catch (Exception e) {
    //         return new ApiResponseData<>(false, "Kiểm tra lại thông tin ");
    //     }
    // }

    @GetMapping("/account/page")
    public ApiResponseData<List<ProductInfoRes>> getPageProducts(@RequestParam int page, int limit ) throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(this.accountService.paginateAccount(page,limit));

    }


    @PutMapping("/admin/account/author")
    public ApiResponseData<String> putAuthor (@RequestBody ChangeAuthor changeAuthor){
        return this.accountService.putAuthor(changeAuthor);
    }

    @PutMapping("/changePassword")
    public ApiResponseData<String> putPassword (@RequestBody ChangePassword changePassword){
        return this.accountService.changePassword(changePassword);
    }

    @PostMapping("/Register")
    public @ResponseBody ApiResponseData<AccountInfoRes> addAccount(@RequestBody AccountModel accountModel) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        accountModel.setAuthor(false);
        return this.accountService.addAccount(accountModel);

    }

    @PostMapping("/admin/Register")
    public @ResponseBody ApiResponseData<AccountInfoRes> addAccountByAdmin(@RequestBody AccountModel account)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // add author
        return this.accountService.addAccount(account);

    }

//    @GetMapping("/account/page")
//    public ApiResponseData<List<AccountInfoRes>> getPageAccount(@RequestBody PaginateReq page){
//        try {
//            return new ApiResponseData<>(this.accountService.paginateAccountOrderByField(page));
//
//        }catch (Exception e){
//            return new ApiResponseData<>(false,"Thông tin lỗi");
//        }
//
//    }


    @GetMapping("/admin/accounts")
    //add author
    public ApiResponseData<List<AccountInfoRes>> getAllAccounts()   {
        try{
            return new  ApiResponseData<>(this.accountService.getAllAccounts());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }

    }



   // add author
    @DeleteMapping("/admin/account")
    public ApiResponseData<AccountInfoRes>deleteAccount (@RequestParam String id) {
       try {
           return  new  ApiResponseData<>( new AccountInfoRes(this.accountService.deleteAccount(id)));
       }catch (Exception e){
           return  new  ApiResponseData<>(false,"Lỗi");
       }

   }








}
