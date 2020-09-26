package com.JavaRestful.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.services.BillService;
import com.JavaRestful.models.components.BillModel;
import java.util.List;
@RestController

public class BillController extends ControllerBridge{
    private final BillService billservice;

    public BillController() {
        this.billservice = new BillService();
    }

    @GetMapping("/bills")
    //add author
    public ApiResponseData<List<BillModel>> getAllBills()   {
        try{
            return new  ApiResponseData<>(this.billservice.getAllBill());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }

    @PostMapping("/bills")
    public @ResponseBody
     ApiResponseData<BillModel> addBill (@RequestBody BillModel bill)  {
         // add author
         BillModel  billModel = this.billservice.addBill(bill);
         if(billModel != null ){
             return new ApiResponseData<>(new BillModel(billModel)) ;
         }else {
 
             return new ApiResponseData<>(false , "Lỗi");
         }
     }

     @DeleteMapping("/bills")
     public ApiResponseData<BillModel>deleteBill (@RequestParam String id) {
        try {
            return  new  ApiResponseData<>( new BillModel(this.billservice.deleteBill(id)));
        }catch (Exception e ){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
 
    }
}
