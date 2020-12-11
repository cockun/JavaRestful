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
import com.JavaRestful.models.response.ProfitRes;

public class ProfitService extends ServiceBridge {

    public ApiResponseData<ProfitModel> getProfit(String dateBegin, String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        BillService billService = new BillService();
        List<BillModel> billModels =  billService.searchBill("nameUser", "", dateBegin, dateEnd);
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
            List<ProfitRes> lProfitRes =  getListProfitRes(billModels,productId);
            ProfitInfoModel profitInfoModel = new ProfitInfoModel();
            profitInfoModel.setIdProduct(productId);
            profitInfoModel.setQuantity(0);
            profitInfoModel.setTotalIn(0);
            profitInfoModel.setTotalOut(0);
            profitInfoModel.setTotalWaiting(0);
            for(ProfitRes profitRes : lProfitRes){
                profitInfoModel.setNameProduct(profitRes.getName());
                    profitInfoModel.setQuantity(profitRes.getQuantity() + profitInfoModel.getQuantity());
                    if(profitRes.isPay()){
                        profitInfoModel.setTotalIn(profitRes.getTotal() + profitInfoModel.getTotalIn());
                    }else{
                        profitInfoModel.setTotalWaiting(profitRes.getTotal() + profitInfoModel.getTotalWaiting());
                    }
                  
                    profitInfoModel.setTotalOut( (long) ( profitRes.getQuantity()*profitRes.getRootPrice() + profitInfoModel.getTotalOut()));
            }
            lisProfitInfoModels.add(profitInfoModel);






            // List<BillInfoModel> billInfoModels = bList.stream().filter(p->p.getIdProduct().equals(productId)).collect(Collectors.toList());
            // ProfitInfoModel profitInfoModel = new ProfitInfoModel();
            // profitInfoModel.setIdProduct(productId);
            // profitInfoModel.setQuantity(0);
            // profitInfoModel.setTotalIn(0);
            // profitInfoModel.setTotalOut(0);
            // billInfoModels.forEach(p->{
            //     profitInfoModel.setNameProduct(p.getNameProduct());
            //     profitInfoModel.setQuantity(p.getQuantity() + profitInfoModel.getQuantity());
            //     profitInfoModel.setTotalIn(p.getTotal() + profitInfoModel.getTotalIn());
            //     profitInfoModel.setTotalOut( (long) ( p.getQuantity()*p.getPriceRoot() + profitInfoModel.getTotalOut()));
            // });
            // lisProfitInfoModels.add(profitInfoModel);
        }
        profitModel.setListProduct(lisProfitInfoModels);
        profitModel.setTotalIn(0); 
        profitModel.setTotalOut(0); 
        profitModel.setTotalWaiting(0); 
        profitModel.getListProduct().forEach(p->{
            profitModel.setTotalIn(profitModel.getTotalIn() + p.getTotalIn()); 
            profitModel.setTotalOut(profitModel.getTotalOut() + p.getTotalOut());
            profitModel.setTotalWaiting(profitModel.getTotalWaiting() + p.getTotalWaiting());
            
        });


    
        return new ApiResponseData<>(profitModel) ;
    }



    public ApiResponseData<List<ProfitRes>> getInfoProfitByProduct(String idProduct ,String dateBegin, String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        BillService billService = new BillService();
        List<BillModel> billModels =  billService.searchBill("nameUser", "", dateBegin, dateEnd);
        if(billModels.isEmpty()){
            return new ApiResponseData<>(null);
        }

        List<ProfitRes> listProfitRes = getListProfitRes(billModels,idProduct);
       
        return new ApiResponseData<>(listProfitRes);
    }


    public List<ProfitRes> getListProfitRes(List<BillModel> billModels, String idProduct){
        List<ProfitRes> listProfitRes = new ArrayList<>();
        billModels.forEach(p->{
            ProfitRes profitRes = new ProfitRes();
            profitRes.setDate(p.getDate());
            profitRes.setUserName(p.getNameUser());
            profitRes.setPay(p.getPay());
            for (BillInfoModel billInfoModel : p.getBillInfoModel()){
                if(billInfoModel.getIdProduct().equals(idProduct)) {
                    profitRes.setName(billInfoModel.getNameProduct());
                    profitRes.setRootPrice(billInfoModel.getPriceRoot());
                    profitRes.setIdProduct(billInfoModel.getIdProduct());
                    profitRes.setDiscount(billInfoModel.getDiscount());
                    profitRes.setQuantity(billInfoModel.getQuantity());
                    profitRes.setTotal(billInfoModel.getTotal());
                    listProfitRes.add(profitRes);    
                }
            }
        }); 

        return listProfitRes;
       
    }


}
