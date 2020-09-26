package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;

import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.AccountService;

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
    public AccountInfoRes login (@RequestBody Login login) throws ExecutionException, InterruptedException {
        return new AccountInfoRes(this.accountService.login(login));
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
    public List<AccountInfoRes> getAllAccounts() throws ExecutionException, InterruptedException {
        return  this.accountService.getAllAccounts();
    }

    /*------------------------------------------*/


    @PostMapping("/account")
   public @ResponseBody
    ResponseEntity<AccountInfoRes> addAccount (@RequestBody AccountModel account)  {
        // add author
        AccountModel   accountModel = this.accountService.addAccount(account);
        if(accountModel != null ){
            return new ResponseEntity<>(new AccountInfoRes(accountModel), HttpStatus.OK) ;
        }else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    /*------------------------------------------*/


   @PutMapping("/account")
   public AccountInfoRes putAccount (@RequestBody AccountModel accountModel){
        try{
            // add author
            return new AccountInfoRes(this.accountService.putAccount(accountModel)) ;
        }catch (Exception e){
            return null;
        }
   }

    /*------------------------------------------*/


   @DeleteMapping("/account")
   // add author
    public AccountInfoRes deleteAccount (@RequestParam String id) throws ExecutionException, InterruptedException {
        return new AccountInfoRes(this.accountService.deleteAccount(id));
   }



}
