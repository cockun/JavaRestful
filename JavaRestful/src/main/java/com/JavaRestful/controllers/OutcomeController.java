package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.components.OutcomeModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.services.IncomeService;
import com.JavaRestful.services.OutcomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutcomeController {

    private final OutcomeService outcomeService;


    public OutcomeController() {
        this.outcomeService = new OutcomeService();
    }

    @GetMapping("/admin/outcomes")
    public ApiResponseData<List<OutcomeModel>> getAllOutcome(){
        return this.outcomeService.getAllOutCome();
    }

    @GetMapping("/admin/outcome/page")
    public ApiResponseData<List<OutcomeModel>> getOutcomePage(PaginateReq page){
        try {
            if(page.isOptionSort() && page.isOptionSearch()){
                return new  ApiResponseData<>(false , "Chỉ sort hoặc search");
            }
            if (page.isOptionSearch()){
                return new ApiResponseData<>(this.outcomeService.paginateOutcomeSearchField (page));
            }
            return new ApiResponseData<>(this.outcomeService.paginateOutcomeOrderByField(page));
        }catch (Exception e){
            return new ApiResponseData<>(false,"Thông tin lỗi");
        }
    }

}
