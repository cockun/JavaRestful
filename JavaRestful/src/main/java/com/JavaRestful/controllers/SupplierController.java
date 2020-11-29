package com.JavaRestful.controllers;

import com.JavaRestful.services.SupplierService;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.JavaRestful.models.components.ApiResponseData;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.JavaRestful.models.components.SupplierModel;


@RestController
public class SupplierController extends ControllerBridge{
    private final SupplierService supplierService;

    public SupplierController() {
        this.supplierService = new SupplierService();
    }

    @GetMapping("admin/supplier")
    public ApiResponseData<List<SupplierModel>> getAllSuppliers() {
        try {
            return new ApiResponseData<>(this.supplierService.getAllSupplier());
        } catch (Exception e) {
            return new ApiResponseData<>(false, e.getMessage());
        }
    }

    @GetMapping("admin/supplier/id")
    public ApiResponseData<SupplierModel> getSupplilerById(@RequestParam String idProduct) {
        try {
            return new ApiResponseData<>(this.supplierService.getSupplierById(idProduct));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @PutMapping("admin/supplier")
    public ApiResponseData<SupplierModel> putSupplier(@RequestBody SupplierModel supplierModel){
        return this.supplierService.putSupplier(supplierModel);
    }

    @DeleteMapping("admin/supplier")
    public ApiResponseData<String> putSupplier(@RequestParam String id){
        return this.supplierService.deleteSupplier(id);
    }
    
    @PostMapping("admin/supplier")
    public ApiResponseData<SupplierModel> addSupplier (@RequestBody SupplierModel supplierModel ){
        return this.supplierService.addSupplier(supplierModel);
    }









}
