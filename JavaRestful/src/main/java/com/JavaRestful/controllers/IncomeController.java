package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.services.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncomeController extends ControllerBridge {

    private final IncomeService incomeService;


    public IncomeController() {
        this.incomeService = new IncomeService();
    }

    @GetMapping("/admin/incomes")
    public ApiResponseData<List<IncomeModel>> getAllIncome(){
        return this.incomeService.getAllInCome();
    }

    @GetMapping("/admin/income/page")
    public ApiResponseData<List<IncomeModel>> getIncomePage(PaginateReq page){
        try {
            if(page.isOptionSort() && page.isOptionSearch()){
                return new  ApiResponseData<>(false , "Chỉ sort hoặc search");
            }
            if (page.isOptionSearch()){
                return new ApiResponseData<>(this.incomeService.paginateIncomeSearchField(page));
            }
            return new ApiResponseData<>(this.incomeService.paginateIncomeOrderByField(page));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Thông tin lỗi");
        }
    }

}
