package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.components.StorageModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.outcome.InputStorageReq;
import com.JavaRestful.services.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class StorageController extends ControllerBridge {

    private final StorageService storageService;


    public StorageController() {
        this.storageService = new StorageService();
    }

    @GetMapping("/admin/storage")
    public ApiResponseData<List<StorageModel>> getAllStorage() throws ExecutionException, InterruptedException {
        return this.storageService.getAllStorage();
    }



    @PostMapping("/admin/storage")
    public ApiResponseData<StorageModel> addStorage(@RequestBody InputStorageReq inputStorageReq){
        return this.storageService.addStorage(inputStorageReq);
    }




}
