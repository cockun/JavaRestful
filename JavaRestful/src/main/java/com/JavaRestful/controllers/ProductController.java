package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ProductModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.products.ProductsInfoChange;
import com.JavaRestful.models.requests.search.SearchReq;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.JavaRestful.models.components.ApiResponseData;

import com.JavaRestful.models.response.account.ProductInfoResAdmin;
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
            return new ApiResponseData<>(false, e.getMessage());
        }

    }

    // search


    //get all sp admin
    @GetMapping("/admin/products")
    
    public ApiResponseData<List<ProductModel>> getAllProductsByAdmin() {
        try {
            return new ApiResponseData<>(this.productservice.getAllProductsByAdmin());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }

    //get sp theo id admin
    @GetMapping("/admin/product")
    
    public ApiResponseData<ProductInfoResAdmin> getProductByAdmin(@RequestParam String id ) {
        try {
            return new ApiResponseData<>(this.productservice.getProductByIdAdmin(id));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }


        //get sp theo id user
    @GetMapping("product")
    public ApiResponseData<ProductInfoRes> getProductById(@RequestParam String id ) {
        try {
            return new ApiResponseData<>(this.productservice.getProductById(id));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }

    }



    //get sp theo id user
    @GetMapping("product/code")
    public ApiResponseData<ProductInfoRes> getIdProductByICode(@RequestParam String value ) throws ExecutionException, InterruptedException {
      return new  ApiResponseData<>(this.productservice.getIdProductByCode(value));

    }




    //them sp
    @PostMapping("/admin/product")
    public ApiResponseData<ProductModel> addProduct(@RequestBody ProductModel product)
            throws InterruptedException, ExecutionException {
        
            return  this.productservice.addProductModel(product);

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

    @GetMapping("/search/products")
    public  ApiResponseData<List<ProductInfoRes>> searchProducts(@RequestParam String field , @RequestParam String value) throws ExecutionException, InterruptedException {
        SearchReq searchReq = new SearchReq(field,value);
        return this.productservice.searchProductsByUser(searchReq);

    }

    @GetMapping("/admin/search/products")
    public  ApiResponseData<List<ProductInfoResAdmin>> searchProductsByAdmin(@RequestParam String field , @RequestParam String value) throws ExecutionException, InterruptedException {
        SearchReq searchReq = new SearchReq(field,value);
        return this.productservice.searchProduct(searchReq);
    }

    @GetMapping("product/paginate")
    public  ApiResponseData<List<ProductInfoResAdmin>> paginateProduct(@RequestParam(defaultValue = "name") String field , @RequestParam(required = false,  defaultValue = "") String value, @RequestParam(defaultValue = "false") Boolean optionSort , @RequestParam(defaultValue = "price") String fieldSort, @RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "10") int limit ) throws ExecutionException, InterruptedException {
        PaginateReq paginateReq = new PaginateReq(field, value, optionSort, fieldSort,page,limit);
        return  this.productservice.paginate(paginateReq);
    }



}

