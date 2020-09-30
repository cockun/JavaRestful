package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.components.StorageModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.outcome.InputStorageReq;
import com.JavaRestful.services.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageController {

    private final StorageService storageService;


    public StorageController() {
        this.storageService = new StorageService();
    }

    @GetMapping("/admin/storage")
    public ApiResponseData<List<IncomeModel>> getAllStorage(){
        return this.storageService.getAllStorage();
    }

    @GetMapping("/admin/storage/page")
    public ApiResponseData<List<StorageModel>> getAllStoragePage(PaginateReq page){
        try {
            if(page.isOptionSort() && page.isOptionSearch()){
                return new  ApiResponseData<>(false , "Chỉ sort hoặc search");
            }
            if (page.isOptionSearch()){
                return new ApiResponseData<>(this.storageService.paginateStorageSearchField(page));
            }
            return new ApiResponseData<>(this.storageService.paginateStorageOrderByField(page));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Thông tin lỗi");
        }
    }

    @PostMapping("/admin/storage")
    public ApiResponseData<StorageModel> addStorage(@RequestBody InputStorageReq inputStorageReq){
        return this.storageService.addStorage(inputStorageReq);
    }

    @DeleteMapping("/admin/storage")
    public ApiResponseData<StorageModel> delete(@RequestParam String id ){
        return this.storageService.deleteStorage(id);
    }


}
