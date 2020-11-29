package com.JavaRestful.controllers;

import com.JavaRestful.models.components.ApiResponseData;

import com.JavaRestful.models.components.CategoryModel;

import com.JavaRestful.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ApiResponseData<List<CategoryModel>> getAllCategories() {
        try {
            return new ApiResponseData<>(this.categoryService.getAllCategories());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @GetMapping("/category")
    public ApiResponseData<CategoryModel> getCategoryByIdProduct(@RequestParam String idProduct) {
        try {
            return new ApiResponseData<>(this.categoryService.getCategoryByIdProduct(idProduct));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @GetMapping("/categorybyid")
    public ApiResponseData<CategoryModel> getCategoryById(@RequestParam String idProduct) {
        try {
            return new ApiResponseData<>(this.categoryService.getCategoryById(idProduct));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @PutMapping("admin/category")
    public ApiResponseData<CategoryModel> putCategory(@RequestBody CategoryModel categoryModel) {
        return this.categoryService.putCategory(categoryModel);
    }

    @DeleteMapping("admin/category")
    public ApiResponseData<String> putCategory(@RequestBody String id) {
        return this.categoryService.deleteCategory(id);
    }

    @PostMapping("admin/category")
    public ApiResponseData<CategoryModel> addCategory(@RequestBody CategoryModel categoryModel) {
        return this.categoryService.addCategory(categoryModel);
    }

}
