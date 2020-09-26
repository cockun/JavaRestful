package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ProductModel;
import com.JavaRestful.services.ProductService;
import com.JavaRestful.services.ServiceBridge;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;


@RestController
public class ProductController  extends ControllerBridge{
    private ProductService productservice;

    public ProductController() {
        this.productservice = new ProductService();
    }
    @GetMapping("/product")
    public ProductModel getProduct(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.productservice.getProductById(id);

        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/product")
   public @ResponseBody ProductModel addProduct (@RequestBody(required = true ) ProductModel product){
        try{
            return this.productservice.addProduct(product);
        }catch (Exception e){
            return null;
        }

       
    }
    @PutMapping("/product")
   public ProductModel purProduct (@RequestBody ProductModel productmodel){
        try{
            return this.productservice.putProduct(productmodel);
        }catch (Exception e){
            return null;
        }
   }
   @DeleteMapping("/product")
    public ProductModel deleteProduct (@RequestParam String id) throws ExecutionException, InterruptedException {
        return this.productservice.deleteProduct(id);
   }


}

