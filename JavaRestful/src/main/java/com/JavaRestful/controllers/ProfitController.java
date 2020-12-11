package com.JavaRestful.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.ProfitModel;
import com.JavaRestful.models.response.ProfitRes;
import com.JavaRestful.services.ProfitService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfitController extends ControllerBridge {
    private final ProfitService profitService;

    public ProfitController() {
        this.profitService = new ProfitService();
    }

    @GetMapping("admin/Profit")
    public ApiResponseData<ProfitModel> getProfit(@RequestParam String dateBegin, @RequestParam String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        return this.profitService.getProfit(dateBegin, dateEnd);
    }


    @GetMapping("admin/ProfitInfo")
    public ApiResponseData<List<ProfitRes>> getProfitProduct(@RequestParam String idProduct,@RequestParam String dateBegin, @RequestParam String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        return this.profitService.getInfoProfitByProduct(idProduct,dateBegin, dateEnd);
    }



    
}
