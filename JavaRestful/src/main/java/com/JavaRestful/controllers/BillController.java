package com.JavaRestful.controllers;

import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.services.BillService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController extends ControllerBridge{
    private BillService billservice;

    public BillController() {
        this.billservice = new BillService();
    }

    @GetMapping("/bill")
    public BillModel getProduct(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.billservice.addBill(2000000,"26-9-2020","COMP101");

        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/bill")
    public @ResponseBody BillModel addBill (@RequestBody(required = true ) BillModel bill){
         try{
             return this.billservice.addBill(bill);
         }catch (Exception e){
             return null;
         }
 
        
     }
}

