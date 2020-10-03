package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.IncomeModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.services.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class IncomeController extends ControllerBridge {

    private final IncomeService incomeService;


    public IncomeController() {
        this.incomeService = new IncomeService();
    }

    @GetMapping("/admin/incomes")
    public ApiResponseData<List<IncomeModel>> getAllIncome() throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(this.incomeService.getAllInCome()) ;
    }



}
