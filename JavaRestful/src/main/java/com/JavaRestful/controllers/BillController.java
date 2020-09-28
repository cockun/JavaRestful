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
import com.JavaRestful.services.ProductService;
import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.models.components.ProductModel;

import java.util.List;

@RestController

public class BillController extends ControllerBridge{
    private final BillService billservice;


    public BillController() {
        this.billservice = new BillService();

    }

    @GetMapping("admin/bills")
    //add author
    public ApiResponseData<List<BillModel>> getAllBill()   {
        try{
            return new  ApiResponseData<>(this.billservice.getAllBill());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }

    @GetMapping("admin/total")
    public ApiResponseData<Double> getMoney()   {
        try{
            return new  ApiResponseData<>(this.billservice.getMoney());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }


    @PostMapping("admin/bills")
    public @ResponseBody
     ApiResponseData<BillModel> addBill (@RequestBody BillModel bill)  {
         // add author
    
         for (int i = 0  ; i < bill.getBillInfoModel().size(); i++){
            String idProduct = bill.getBillInfoModel().get(i).getIdProduct();
            ProductService productservice = new ProductService();
            ProductModel p = productservice.getProductDocumentByIdProduct(idProduct);
            bill.getBillInfoModel().get(i).setCode(p.getCode());
            bill.getBillInfoModel().get(i).setDetail(p.getDetail());
            bill.getBillInfoModel().get(i).setDiscount(p.getDiscount());
            bill.getBillInfoModel().get(i).setPrice(p.getPrice());
            bill.getBillInfoModel().get(i).setPriceRoot(p.getRootprice());
            bill.getBillInfoModel().get(i).setNameProduct(p.getName());
         }
         BillModel  billModel = this.billservice.addBill(bill);
         
         if(billModel != null ){
             return new ApiResponseData<>(new BillModel(billModel)) ;
         }else {
 
             return new ApiResponseData<>(false , "Lỗi");
         }
     }

     @DeleteMapping("admin/bills")
     public ApiResponseData<BillModel>deleteBill (@RequestParam String id) {
        try {
            return  new  ApiResponseData<>( new BillModel(this.billservice.deleteBill(id)));
        }catch (Exception e ){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
    }

    
}
