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
    @PutMapping("/admin/product")
    public ApiResponseData<ProductModel>  putAccount (@RequestBody ProductsInfoChange product){
        try{

            return new  ApiResponseData<>((this.productservice.putProduct(product))) ;
        }catch (Exception e){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
    }



    @GetMapping("/products")
    // add author
    public ApiResponseData<List<ProductInfoRes>> getAllProducts() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProducts());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }



    @GetMapping("/admin/products")
    // add author
    public ApiResponseData<List<ProductModel>> getAllAccountsByAdmin() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProductsByAdmin());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }

    // @GetMapping("/product")
    // // add author
    // public ApiResponseData<ProductInfoRes> getProduct(@RequestParam String id ) {
    //     try {
    //         return new ApiResponseData<>(this.productservice.getProductById(id));
    //     } catch (Exception e) {
    //         return new ApiResponseData<>(false, "Lỗi");
    //     }

    // }
    @GetMapping("/admin/product")
    // add author
    public ApiResponseData<ProductModel> getProductByAdmin(@RequestParam String id ) {
        try {
            return new ApiResponseData<>(this.productservice.getProductByIdAdmin(id));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }



    // @GetMapping("/product")
    // public List<ProductModel> getProduct(@RequestParam String name)
    // {
    // return this.productservice.findProduct(name);
    // }

    @PostMapping("/product")
    public @ResponseBody ApiResponseData<ProductModel> addAccount(@RequestBody ProductModel product)
            throws InterruptedException, ExecutionException {
        // add author
        ProductModel   productmodel = this.productservice.addProductModel(product);
        if(productmodel != null ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "Kiểm tra lại tài khoản mật khẩu");
        }

    }
    @PostMapping("/products")
    public @ResponseBody ApiResponseData <List<ProductModel>>  addMultiProduct(@RequestBody  List<ProductModel> product)
            throws InterruptedException, ExecutionException {
        // add author
        List <ProductModel> productmodel =this.productservice.addMultiProduct(product);
        if(!productmodel.isEmpty() ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "Error");
        }

    }



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
    //SADASDASD



}

