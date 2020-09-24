package com.JavaRestful.controllers;


import com.JavaRestful.models.AccountModel;
import com.JavaRestful.services.AccountService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
public class AccountController  extends ControllerBridge{
    private AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    @GetMapping("/account")
    public AccountModel getAccount(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.accountService.getAccountById(id)   ;

        }catch (Exception e){
            return null;
        }
    }


    @PostMapping("/account")
   public @ResponseBody AccountModel addAccount (@RequestBody(required = true ) AccountModel account){
        try{
            this.accountService.addAccount(account);
            return account;
        }catch (Exception e){
            return null;
        }

    }

   @PutMapping("/account")
   public AccountModel putAccount (@RequestBody AccountModel accountModel){
        try{
            return this.accountService.putAccount(accountModel);
        }catch (Exception e){
            return null;
        }
   }
   @DeleteMapping("/account")
    public boolean deleteAccount (@RequestParam String id) throws ExecutionException, InterruptedException {
        return this.accountService.deleteAccount(id);
   }

}
