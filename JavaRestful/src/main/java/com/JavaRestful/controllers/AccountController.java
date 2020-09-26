package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.AccountService;

import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
public class AccountController  extends ControllerBridge{
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }


    @PostMapping("/login")
    public ApiResponseData<AccountInfoRes> login (@RequestBody Login login) throws ExecutionException, InterruptedException {
        AccountModel accountModel = this.accountService.login(login);
        if(accountModel != null){
            return  new  ApiResponseData<AccountInfoRes>(new AccountInfoRes(accountModel));
        }else {
            return new  ApiResponseData<AccountInfoRes>(false,"Sai tên tài khoản hoặc mật khẩu");
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
    public ApiResponseData<List<AccountInfoRes>> getAllAccounts() throws ExecutionException, InterruptedException {
        try{
            return new  ApiResponseData<List<AccountInfoRes>>(this.accountService.getAllAccounts());
        }catch (Exception e){
            return new  ApiResponseData<List<AccountInfoRes>>(false,"Lỗi");
        }

    }

    /*------------------------------------------*/


    @PostMapping("/account")
   public @ResponseBody
    ApiResponseData<AccountInfoRes> addAccount (@RequestBody AccountModel account)  {
        // add author
        AccountModel   accountModel = this.accountService.addAccount(account);
        if(accountModel != null ){
            return new ApiResponseData<AccountInfoRes>(new AccountInfoRes(accountModel)) ;
        }else {

            return new ApiResponseData<AccountInfoRes>(false , "Kiểm tra lại tài khoản mật khẩu");
        }

    }


    /*------------------------------------------*/


   @PutMapping("/account")
   public ApiResponseData<AccountInfoRes>  putAccount (@RequestBody AccountModel accountModel){
        try{
            // add author
            return new  ApiResponseData<AccountInfoRes>( new AccountInfoRes(this.accountService.putAccount(accountModel))) ;
        }catch (Exception e){
            return  new  ApiResponseData<AccountInfoRes>(false,"Lỗi");
        }
   }

    /*------------------------------------------*/

   // add author
    @DeleteMapping("/account")
    public ApiResponseData<AccountInfoRes>deleteAccount (@RequestParam String id) throws ExecutionException, InterruptedException {
       try {
           return  new  ApiResponseData<AccountInfoRes>( new AccountInfoRes(this.accountService.deleteAccount(id)));
       }catch (Exception e ){
           return  new  ApiResponseData<AccountInfoRes>(false,"Lỗi");
       }

   }



}
