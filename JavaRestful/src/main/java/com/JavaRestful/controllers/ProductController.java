package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ProductModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.products.ProductsInfoChange;
import com.JavaRestful.models.response.account.AccountInfoRes;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController extends ControllerBridge {
    private final ProductService productservice;

    public ProductController() {
        this.productservice = new ProductService();
    }
    //sua
    @PutMapping("/admin/product")
    public ApiResponseData<ProductModel>  putAccount (@RequestBody ProductsInfoChange product){
        try{

            return new  ApiResponseData<>((this.productservice.putProduct(product))) ;
        }catch (Exception e){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
    }


    // get all sp user

    @GetMapping("/products")
    public ApiResponseData<List<ProductInfoRes>> getAllProducts() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProducts());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }


    //get all sp admin
    @GetMapping("/admin/products")
    
    public ApiResponseData<List<ProductModel>> getAllAccountsByAdmin() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProductsByAdmin());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }

    //get sp theo id admin
    @GetMapping("/admin/product")
    
    public ApiResponseData<ProductModel> getProductByAdmin(@RequestParam String id ) {
        try {
            return new ApiResponseData<>(this.productservice.getProductByIdAdmin(id));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }
        //get sp theo id user
    @GetMapping("product")
    public ApiResponseData<ProductInfoRes> getProductByUser(@RequestParam String id ) {
        try {
            return new ApiResponseData<>(this.productservice.getProductById(id));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }



    //them sp
    @PostMapping("/admin/product")
    public @ResponseBody ApiResponseData<ProductModel> addAccount(@RequestBody ProductModel product)
            throws InterruptedException, ExecutionException {
        
        ProductModel   productmodel = this.productservice.addProductModel(product);
        if(productmodel != null ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "ERROR");
        }

    }
    // them nhieu sp
    @PostMapping("/admin/products")
    public @ResponseBody ApiResponseData <List<ProductModel>>  addMultiProduct(@RequestBody  List<ProductModel> product)
            throws InterruptedException, ExecutionException {
        
        List <ProductModel> productmodel =this.productservice.addMultiProduct(product);
        if(!productmodel.isEmpty() ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "Error");
        }

    }


    //phan trang
    @GetMapping("/produtcs/page")
    public ApiResponseData<List<ProductInfoRes>> getPageProduts(@RequestBody PaginateReq page){
        try {
            if(page.isOptionSort() && page.isOptionSearch()){
                return new  ApiResponseData<>(false , "Chỉ sort hoặc search");
            }
            if (page.isOptionSearch()){
                return new ApiResponseData<>(this.productservice.paginateProductSearchField(page));
            }

            return new ApiResponseData<>(this.productservice.paginateProductOrderByField(page));

        }catch (Exception e){
            return new ApiResponseData<>(false,"Thông tin lỗi");
        }

    }
    // xóa product
    @DeleteMapping("/admin/product")
    public ApiResponseData<ProductModel> deleteProduct(@RequestParam String id )
    {
        try{
            return new ApiResponseData<>(this.productservice.deleteProduct(id));
        }
        catch(Exception e)
        {   
            return new ApiResponseData<>(false,"ERROR");
        }
    }



}

