package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.AccountService;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
public class AccountController  extends ControllerBridge{
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }


    @PostMapping("/login")
    public ApiResponseData<AccountInfoRes> login (@RequestBody Login login)   {
        return this.accountService.login(login);
        //return token
    }

    @GetMapping("/account")
    public ApiResponseData<AccountInfoRes>  getAccount(@RequestParam String id )  {

        //add author
        try{
         return new  ApiResponseData<>(new AccountInfoRes(this.accountService.getAccountById(id)));

        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
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

    /*------------------------------------------*/


    @PostMapping("/account")
   public @ResponseBody
    ApiResponseData<AccountInfoRes> addAccountByAdmin (@RequestBody AccountModel account)  {
        // add author
            return this.accountService.addAccount(account);

    }

    /*------------------------------------------*/


   @PutMapping("/account")
   public ApiResponseData<AccountInfoRes>  putAccount (@RequestBody AccountModel accountModel){
        try{
            // add author
            return new  ApiResponseData<>( new AccountInfoRes(this.accountService.putAccount(accountModel))) ;
        }catch (Exception e){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
   }

    /*------------------------------------------*/

   // add author
    @DeleteMapping("/admin/account")
    public ApiResponseData<AccountInfoRes>deleteAccount (@RequestParam String id) {
       try {
           return  new  ApiResponseData<>( new AccountInfoRes(this.accountService.deleteAccount(id)));
       }catch (Exception e){
           return  new  ApiResponseData<>(false,"Lỗi");
       }

   }

   @PostMapping("/account/page")
    public ApiResponseData<List<AccountInfoRes>> getPageAccount(@RequestBody PaginateReq page){
        try {
            return new ApiResponseData<>(this.accountService.paginateAccountOrderByField(page));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Lỗi");
        }

   }




}
