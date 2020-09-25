package com.JavaRestful.controllers;


import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.services.AccountService;
import com.JavaRestful.services.ServiceBridge;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
public class AccountController  extends ControllerBridge{
    private final AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    @GetMapping("/account")
    public AccountModel getAccount(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.accountService.getAccountById("KspENvqCLefS7MSHY9ov");

        }catch (Exception e){
            return null;
        }

    }


    @PostMapping("/account")
   public @ResponseBody AccountModel addAccount (@RequestBody AccountModel account){
        try{




            return  this.accountService.addAccount(account);
        }catch (Exception e){
            return null;
        }

    }
    
   @PutMapping("/account")
   public AccountModel putAccount (@RequestBody AccountModel accountModel){
        try{

            this.accountService.getAccountById("KspENvqCLefS7MSHY9ov").setName("BestCoc");
            return this.accountService.putAccount(this.accountService.getAccountById("KspENvqCLefS7MSHY9ov"));
        }catch (Exception e){
            return null;
        }
   }
   @DeleteMapping("/account")
    public AccountModel deleteAccount (@RequestParam String id) throws ExecutionException, InterruptedException {
        return this.accountService.deleteAccount(id);
   }

}
