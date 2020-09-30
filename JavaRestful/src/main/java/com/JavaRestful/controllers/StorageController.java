//package com.JavaRestful.controllers;
//
//import com.JavaRestful.models.components.ApiResponseData;
//import com.JavaRestful.models.components.IncomeModel;
//import com.JavaRestful.models.components.StorageModel;
//import com.JavaRestful.models.requests.PaginateReq;
//import com.JavaRestful.models.requests.outcome.InputStorageReq;
//import com.JavaRestful.services.StorageService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class StorageController {
//
//    private final StorageService storageService;
//
//
//    public StorageController() {
//        this.storageService = new StorageService();
//    }
//
//    @GetMapping("/admin/incomes")
//    public ApiResponseData<List<IncomeModel>> getAllIncome(){
//        return this.storageService.getAllStorage();
//    }
//
//    @GetMapping("/admin/income/page")
//    public ApiResponseData<List<StorageModel>> getIncomePage(PaginateReq page){
//        try {
//            if(page.isOptionSort() && page.isOptionSearch()){
//                return new  ApiResponseData<>(false , "Chỉ sort hoặc search");
//            }
//            if (page.isOptionSearch()){
//                return new ApiResponseData<>(this.storageService.paginateStorageSearchField(page));
//            }
//            return new ApiResponseData<>(this.storageService.paginateStorageSearchField(page));
//        }catch (Exception e){
//            return new ApiResponseData<>(false,"Thông tin lỗi");
//        }
//    }
//
//    @PostMapping("/admin/storage")
//    public ApiResponseData<StorageModel> addStorage(@RequestBody InputStorageReq inputStorageReq){
//
//        return null;
//
//
//    }
//
//
//
//
//
//
//
//}
