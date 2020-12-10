package com.JavaRestful.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.BillInfoModel;
import com.JavaRestful.models.components.BillModel;
import com.JavaRestful.models.components.ProfitInfoModel;
import com.JavaRestful.models.components.ProfitModel;

public class ProfitService extends ServiceBridge {

    public ApiResponseData<ProfitModel> getProfit(String dateBegin, String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        BillService billService = new BillService();
        List<BillModel> billModels =  billService.searchBill("nameUser", "t", dateBegin, dateEnd);
        if(billModels.isEmpty()){
            return new ApiResponseData<>(null);
        }
        List<BillInfoModel> bList = new ArrayList<>();
        ProfitModel profitModel = new ProfitModel();
        for (BillModel billModel : billModels){
            bList = Stream.concat(bList.stream(), billModel.getBillInfoModel().stream()).collect(Collectors.toList());
        }

        List<ProfitInfoModel> lisProfitInfoModels = new ArrayList<>();
        List<String> productIds = bList.stream().map(p->p.getIdProduct()).distinct().collect(Collectors.toList());
        for(String productId : productIds){
            List<BillInfoModel> billInfoModels = bList.stream().filter(p->p.getIdProduct().equals(productId)).collect(Collectors.toList());
            ProfitInfoModel profitInfoModel = new ProfitInfoModel();
            profitInfoModel.setIdProduct(productId);
            profitInfoModel.setQuantity(0);
            profitInfoModel.setTotalIn(0);
            profitInfoModel.setTotalOut(0);
            billInfoModels.forEach(p->{
                profitInfoModel.setNameProduct(p.getNameProduct());
                profitInfoModel.setQuantity(p.getQuantity() + profitInfoModel.getQuantity());
                profitInfoModel.setTotalIn(p.getTotal() + profitInfoModel.getTotalIn());
                profitInfoModel.setTotalOut( (long) ( p.getQuantity()*p.getPriceRoot() + profitInfoModel.getTotalOut()));
            });
            lisProfitInfoModels.add(profitInfoModel);
        }
        profitModel.setListProduct(lisProfitInfoModels);
        profitModel.setTotalIn(0); 
        profitModel.setTotalOut(0); 
        profitModel.getListProduct().forEach(p->{
            profitModel.setTotalIn(profitModel.getTotalIn() + p.getTotalIn()); 
            profitModel.setTotalOut(profitModel.getTotalOut() + p.getTotalOut());
        });


        



    
        return new ApiResponseData<>(profitModel) ;
    }



}
