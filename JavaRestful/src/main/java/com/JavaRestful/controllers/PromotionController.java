package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.PromotionModel;

import com.JavaRestful.services.PromotionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PromotionController extends ControllerBridge {
    private final PromotionService promotionService;

    public PromotionController() {
        this.promotionService = new PromotionService();
    }



    @GetMapping("/promotions")
    public ApiResponseData<PromotionModel> getPromotion(@RequestParam String id )  {

        try{
            PromotionModel promo = this.promotionService.getPromotionById(id);
            return new  ApiResponseData<>(promo);

        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }
    @PutMapping("/admin/promotion")
    public ApiResponseData<PromotionModel> putPromotion (@RequestBody PromotionModel promotionModel){
        try{
            // add author
            return new  ApiResponseData<>(this.promotionService.putPromotion(promotionModel)) ;
        }catch (Exception e){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
    }

    @PostMapping("/admin/promotion")
    public @ResponseBody
    ApiResponseData<PromotionModel> addPromotion (@RequestBody PromotionModel promotionModel)  {
        // add author
        return this.promotionService.addPromotion(promotionModel);

    }


    @GetMapping("/admin/promotions")
    //add author
    public ApiResponseData<List<PromotionModel>> getAllPromotion()   {
        try{
            return new  ApiResponseData<>(this.promotionService.getAllPromotion());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }

    }

    // add author
    @DeleteMapping("/admin/promotion")
    public ApiResponseData<PromotionModel>deleteAccount (@RequestParam String id) {
        try {
            return  new  ApiResponseData<>( this.promotionService.deletePromotion(id));
        }catch (Exception e){
            return  new  ApiResponseData<>(false,"Lỗi");
        }

    }


    @GetMapping("/admin/promotionByCode")

    public ApiResponseData<PromotionModel> getPromotionByCode(@RequestParam String code)   {
        try{
            return new  ApiResponseData<>(this.promotionService.getPromotionByCode(code));
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }

    }

}
