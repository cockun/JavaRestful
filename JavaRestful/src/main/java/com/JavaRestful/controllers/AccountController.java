package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.AccountService;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class AccountController  extends ControllerBridge{
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }


    @PostMapping("/login")
    public ApiResponseData<AccountInfoRes> login (@RequestBody Login login)   {
        AccountModel accountModel = this.accountService.login(login);
        if(accountModel != null){
            return  new  ApiResponseData<>(new AccountInfoRes(accountModel));
        }else {
            return new  ApiResponseData<>(false,"Sai tên tài khoản hoặc mật khẩu");
        }


        //return token
    }

    @GetMapping("/account")
    public AccountInfoRes getAccount(@RequestParam String id )  {

        //add author
        try{
         return new AccountInfoRes(this.accountService.getAccountById(id));

        }catch (Exception e){
            return null;
        }

    }

    @GetMapping("/accounts")
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
    ApiResponseData<AccountInfoRes> addAccount (@RequestBody AccountModel account)  {
        // add author
        AccountModel   accountModel = this.accountService.addAccount(account);
        if(accountModel != null ){
            return new ApiResponseData<>(new AccountInfoRes(accountModel)) ;
        }else {

            return new ApiResponseData<>(false , "Kiểm tra lại tài khoản mật khẩu");
        }

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
    @DeleteMapping("/account")
    public ApiResponseData<AccountInfoRes>deleteAccount (@RequestParam String id) {
       try {
           return  new  ApiResponseData<>( new AccountInfoRes(this.accountService.deleteAccount(id)));
       }catch (Exception e ){
           return  new  ApiResponseData<>(false,"Lỗi");
       }

   }



}
