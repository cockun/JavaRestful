package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ProductModel;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.requests.account.Login;
import com.JavaRestful.services.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController extends ControllerBridge {
    private final ProductService productservice;

    public ProductController() {
        this.productservice = new ProductService();
    }

    @GetMapping("/products")
    // GET USER
    public ApiResponseData<List<ProductInfoRes>> getAllProduct() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProducts());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }

    @GetMapping("/find/product")
    //tìm sản phẩm user
    public List<ProductModel> getProduct(@RequestParam String name)
    {
    return this.productservice.findProduct(name);
    }


    @GetMapping("/admin/products")
    public ApiResponseData<List<ProductModel>> GetAllProduct()
    {
        try {
            return new ApiResponseData<>(this.productservice.getAllProducts2());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }



    @PostMapping("/admin/product")
    public @ResponseBody ApiResponseData<ProductModel> addAccount(@RequestBody ProductModel product)
            throws InterruptedException, ExecutionException {
        // thêm từng sản phẩm -- admin
        ProductModel   productmodel = this.productservice.addProductModel(product);
        if(productmodel != null ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "  Error");
        }

    }
    // thêm nhiều sản phẩm
    @PostMapping("/products")
    public @ResponseBody ApiResponseData <List<ProductModel>>  addMultiProduct(@RequestBody  List<ProductModel> product)
            throws InterruptedException, ExecutionException {
        List <ProductModel> productmodel =this.productservice.addMultiProduct(product);
        if(!productmodel.isEmpty() ){
            return new ApiResponseData<>(productmodel) ;
        }else {

            return new ApiResponseData<>(false , "Error");
        }

    }
    // xóa sản phẩm
    @DeleteMapping("/admin/product")
    public ApiResponseData<ProductModel>deleteProduct (@RequestParam String id) {
        try {
            ProductModel product = this.productservice.deleteProduct(id);
            return  new  ApiResponseData<>(product);
        }catch (Exception e ){
            return  new  ApiResponseData<>(false,"Lỗi");
        }
 
    }

    



}

