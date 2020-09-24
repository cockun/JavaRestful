package com.JavaRestful.controllers;


import com.JavaRestful.models.ProductModel;
import com.JavaRestful.services.ProductService;

import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController  extends ControllerBridge{
    private ProductService productservice;

    public ProductController() {
        this.productservice = new ProductService();
    }
    @GetMapping("/product")
    public ProductModel getProduct(@RequestParam(value = "id" , defaultValue = "") String id )  {
        try{
         return this.productservice.getProductById(id)   ;

        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/product")
   public @ResponseBody ProductModel addProduct (@RequestBody(required = true ) ProductModel product){
        try{
            this.productservice.addProduct("maximum",2050,11020,"hay","ga",2050,"maxdx");
            return product;
        }catch (Exception e){
            return null;
        }

       
    }


//   @PutMapping("/account")
//   public AccountModel putAccount (@RequestBody AccountModel account){
//        try{
//
//        }
//   }

}

