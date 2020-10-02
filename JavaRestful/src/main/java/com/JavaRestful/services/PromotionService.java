package com.JavaRestful.services;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.PromotionModel;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PromotionService extends ServiceBridge {



    public CollectionReference getPromotionCollection (){

        return getFirebase().collection("Promotions");
    }



    public DocumentReference getPromotionDocumentById(String id )   {
        return getDocumentById("Promotions",id);
    }

    public List<PromotionModel> findPromotionByCode(String code){
        try{
            return getPromotionCollection().whereEqualTo("promotionCode",code).get().get().toObjects(PromotionModel.class);
        }catch (Exception e){
            return null;
        }
    }

    public  PromotionModel getPromotionById(String id) {
        try {
            return getPromotionDocumentById(id).get().get().toObject(PromotionModel.class);
        }catch (Exception  e){
            return null;
        }
    }

    public  PromotionModel getPromotionByCode(String code) {
        try {
            return getPromotionCollection().whereEqualTo("promotionCode",code).get().get().toObjects(PromotionModel.class).get(0);
        }catch (Exception  e){
            return null;
        }
    }



    public ApiResponseData<PromotionModel> addPromotion(PromotionModel promotionModel ) {


        if(!findPromotionByCode(promotionModel.getPromotionCode()).isEmpty()){
            return new ApiResponseData<>(false,"Promotion Code đã tồn tại");
        }

        if(promotionModel.isPromotionCategory()){
            if(promotionModel.getDiscount() >100){
                return new ApiResponseData<>(false,"Giảm giá theo % không thể lớn hơn 100%");
            }
        }

        promotionModel.setId(randomDocumentId("Promotions"));
        getPromotionDocumentById(promotionModel.getId()).set(promotionModel);
        return  new ApiResponseData<>(promotionModel ) ;

    }

    public PromotionModel putPromotion(PromotionModel promotionModel)  {
        getPromotionCollection().document(promotionModel.getId()).set(promotionModel);
        return promotionModel;
    }

    public  List<PromotionModel> getAllPromotion() throws ExecutionException, InterruptedException {

        return getPromotionCollection().orderBy("promotionCode").get().get().toObjects(PromotionModel.class);


    }

    public PromotionModel deletePromotion(String id) throws ExecutionException, InterruptedException {
        PromotionModel promotionModel;
        promotionModel = getDocumentById("Promotions",id).get().get().toObject(PromotionModel.class);
        deleteDocument("Promotions",id);
        return promotionModel;
    }




    /*------------------------------------------------------------------------------*/




}
