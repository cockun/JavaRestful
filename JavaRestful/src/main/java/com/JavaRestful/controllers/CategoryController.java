package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;

import com.JavaRestful.models.components.CategoryModel;

import com.JavaRestful.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController extends ControllerBridge {

    private final CategoryService categoryService;


    public CategoryController() {
        this.categoryService = new CategoryService();

    }


    @GetMapping("/categories")
    public ApiResponseData<List<CategoryModel>> getAllCategories()   {
        try{
            return new  ApiResponseData<>(this.categoryService.getAllCategories());
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }

    @GetMapping("/category")
    public ApiResponseData<CategoryModel> getCategoryByIdProduct(@RequestParam String idProduct)   {
        try{
            return new  ApiResponseData<>(this.categoryService.getCategoryByIdProduct(idProduct));
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }

    @GetMapping("/categorybyid")
    public ApiResponseData<CategoryModel> getCategoryById(@RequestParam String idProduct)   {
        try{
            return new  ApiResponseData<>(this.categoryService.getCategoryById(idProduct));
        }catch (Exception e){
            return new  ApiResponseData<>(false,"Lỗi");
        }
    }





}
