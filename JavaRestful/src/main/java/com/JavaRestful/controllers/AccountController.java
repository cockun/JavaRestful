package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.account.AccountInfoChange;
import com.JavaRestful.models.requests.account.ChangeAuthor;
import com.JavaRestful.models.requests.account.RegisterByUserReq;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.AccountService;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController

public class AccountController extends ControllerBridge {
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    @GetMapping("/login")
    public  ApiResponseData<AccountInfoRes> login(@RequestBody Login login) {
        return this.accountService.login(login);
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
            // add author
            return new ApiResponseData<>(new AccountInfoRes(this.accountService.putAccount(accountInfoChange)));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Kiểm tra lại thông tin ");
        }
    }

    @PostMapping("/admin/account/author")
    public ApiResponseData<String> putAuthor (@RequestBody ChangeAuthor changeAuthor){
        return this.accountService.putAuthor(changeAuthor);
    }

    @PostMapping("/Register")
    public @ResponseBody ApiResponseData<AccountInfoRes> addAccount(@RequestBody RegisterByUserReq registerByUserReq)
             {
        // add author
        return this.accountService.addAccountByUser(registerByUserReq);

    }

    @PostMapping("/admin/Register")
    public @ResponseBody ApiResponseData<AccountInfoRes> addAccountByAdmin(@RequestBody AccountModel account)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // add author
        return this.accountService.addAccountByAdmin(account);

    }

    @GetMapping("/account/page")
    public ApiResponseData<List<AccountInfoRes>> getPageAccount(@RequestBody PaginateReq page){
        try {
            return new ApiResponseData<>(this.accountService.paginateAccountOrderByField(page));

        }catch (Exception e){
            return new ApiResponseData<>(false,"Thông tin lỗi");
        }

    }


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
