package com.JavaRestful.controllers;


import com.JavaRestful.models.AccountModel;
import com.JavaRestful.services.AccountService;
import com.JavaRestful.services.ServiceBridge;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController  extends ControllerBridge{
    private AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }
    @GetMapping("/account")
    public AccountModel getAccount(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.accountService.addAccount("max","dead","max","max","max",true) ;

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
  

    
//   @PutMapping("/account")
//   public AccountModel putAccount (@RequestBody AccountModel account){
//        try{
//
//        }
//   }

}
