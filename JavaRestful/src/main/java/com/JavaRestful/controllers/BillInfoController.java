package com.JavaRestful.controllers;

import com.JavaRestful.services.BillInfoService;
import com.JavaRestful.models.components.BillInfoModel;
import java.util.ArrayList;
import java.util.List;

import com.JavaRestful.models.components.ApiResponseData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillInfoController extends ControllerBridge {
    private final BillInfoService billinfoservice;
    public BillInfoController() {
        this.billinfoservice = new BillInfoService();
    }

    @PostMapping("/billinfo")
    public @ResponseBody
     ApiResponseData<ArrayList<BillInfoModel>> addBillInfo (@RequestBody ArrayList<BillInfoModel> billInfo)  {
        //  // add author
        //  BillModel  billModel = this.billservice.addBill(bill);

        ArrayList<BillInfoModel> billinfoModel = this.billinfoservice.addBillInfo(billInfo);
        if(billinfoModel != null ){
            return new ApiResponseData<>(new  ArrayList<BillInfoModel>(billinfoModel)) ;
        }else {
            return new ApiResponseData<>(false , "Lỗi");
        }
     }

     @GetMapping("/billinfos")
     //add author
     public ApiResponseData<List<BillInfoModel>> getAllBillInfo()   {
         try{
             return new  ApiResponseData<>(this.billinfoservice.getAllBillInfo());
         }catch (Exception e){
             return new  ApiResponseData<>(false,"Lỗi");
         }
     }

    //  @GetMapping("/billinfos")
    //  public ApiResponseData<List<BillInfoModel>>getBillInFoByBillId (@RequestParam String id) {
    //     try {
    //         return  new  ApiResponseData<>(this.billinfoservice.getBillInFoByBillId(id));
    //     }catch (Exception e ){
    //         return  new  ApiResponseData<>(false,"Lỗi");
    //     }
 
    // }
}
