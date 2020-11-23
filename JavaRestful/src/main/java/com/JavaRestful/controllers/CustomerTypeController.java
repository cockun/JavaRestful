package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.CustomerTypeModel;
import com.JavaRestful.services.CustomerTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerTypeController extends ControllerBridge{
    private final CustomerTypeService customerTypeService;
    public CustomerTypeController() {
        this.customerTypeService = new CustomerTypeService();
    }

    @PostMapping("/customer")
    public ApiResponseData<CustomerTypeModel> addCustomerType(@RequestBody CustomerTypeModel customerTypeModel){
        return this.customerTypeService.addCustomerType(customerTypeModel);
    }
    @DeleteMapping("/customer")
    public ApiResponseData<String> deleteCustomerType(@RequestParam String id){
        return this.customerTypeService.deleteCustomerType(id);
    }

    @PutMapping("/customer")
    public  ApiResponseData<String> putCustomerType (@RequestBody CustomerTypeModel customerTypeModel ){
        return  this.customerTypeService.putCustomer(customerTypeModel);

    }


}
