package com.JavaRestful.services;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.CategoryModel;
import com.JavaRestful.models.components.ProductModel;

import com.JavaRestful.models.components.ReviewModel;
import com.JavaRestful.models.requests.PaginateReq;
import com.JavaRestful.models.requests.products.ProductsInfoChange;

import com.JavaRestful.models.requests.search.SearchReq;
import com.JavaRestful.models.response.account.ProductInfoRes;
import com.JavaRestful.models.response.account.ProductInfoResAdmin;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductService extends ServiceBridge {
    public CollectionReference getProductCollection() {

        return getFirebase().collection("Products");
    }

    public DocumentReference getProductDocumentById(String id) {
        return getDocumentById("Products", id);
    }

    public ProductModel getProductDocumentByUser(String name) {
        try {
            List<ProductModel> productmodel = getProductCollection().whereEqualTo("name", name).get().get()
                    .toObjects((ProductModel.class));
            if (productmodel.isEmpty()) {
                return null;
            } else {
                return productmodel.get(0);
            }
        } catch (Exception e) {
            return null;
        }

    }

    public List<ProductModel> findProduct(String name) {

        try {

            return getProductCollection().whereGreaterThanOrEqualTo("name", name).limit(5).get().get()
                    .toObjects(ProductModel.class);
        } catch (Exception e) {
            return null;
        }

    }

    public List<ProductInfoRes> searchProductByField(String field, String value) {
        try {
            return getProductCollection().whereEqualTo(field, value).get().get().toObjects(ProductInfoRes.class);

        } catch (Exception e) {
            return null;
        }
    }

    public ProductModel addProductModel(ProductModel productmodel) throws InterruptedException, ExecutionException {
        if (productmodel.getName() == null || !getFirebase().collection("Products")
                .whereEqualTo("name", productmodel.getName()).get().get().toObjects(ProductModel.class).isEmpty()) {
            return null;
        } else {
            // category
            List<ProductModel> product = getFirebase().collection("Category")
                    .whereEqualTo("name", productmodel.getIdcategory()).get().get().toObjects(ProductModel.class);
            if (product.isEmpty()) {
                CategoryModel category = new CategoryModel();
                category.setId(randomDocumentId("Category"));

                category.setName(productmodel.getIdcategory());
                getFirebase().collection("Category").document(category.getId()).set(category);
                productmodel.setIdcategory(category.getId());
            } else {
                productmodel.setIdcategory(product.get(0).getId());
            }
            // add

            productmodel.setCode(HelpUtility.getRandomCode("SP"));

            productmodel.setDate(java.time.LocalDate.now().toString());
            productmodel.setId(randomDocumentId("Products"));
            getProductDocumentById(productmodel.getId()).set(productmodel);
            return productmodel;
        }
    }

    public List<ProductModel> addMultiProduct(List<ProductModel> products)
            throws InterruptedException, ExecutionException {
        for (ProductModel product : products) {
            addProductModel(product);
        }
        return products;
    }

    public ProductInfoRes getProductById(String id) throws InterruptedException, ExecutionException {
        ProductInfoResAdmin productInfoResAdmin = getProductByIdAdmin(id);
        ProductInfoRes productInfoRes = new ProductInfoRes(productInfoResAdmin);
        return productInfoRes;

    }

    public ProductInfoRes getIdProductByCode(String code) throws ExecutionException, InterruptedException {
        return getProductCollection().whereEqualTo("code", code).get().get().toObjects(ProductInfoRes.class).get(0);

    }

    public ProductInfoResAdmin getProductByIdAdmin(String id) throws InterruptedException, ExecutionException {

        ProductInfoResAdmin productInfoResAdmin = getDocumentById("Products", id).get().get()
                .toObject(ProductInfoResAdmin.class);
        CategoryModel categoryModel = getFirebase().collection("Category").document(productInfoResAdmin.getIdcategory())
                .get().get().toObject(CategoryModel.class);
        productInfoResAdmin.setIdcategory(categoryModel.getName());
        productInfoResAdmin.setReviewPoint(getPointProduct(productInfoResAdmin.getId()));
        return productInfoResAdmin;

    }

    public List<ProductInfoRes> getAllProducts() throws ExecutionException, InterruptedException {

        List<ProductInfoRes> list = getProductCollection().orderBy("name").get().get().toObjects(ProductInfoRes.class);
        var categoryIds = list.stream().map(p -> p.getIdcategory()).collect(Collectors.toList());
        var categories = getCategoryProduct(categoryIds);
        for (ProductInfoRes productInfoResAdmin : list) {
            var tmp = categories.stream().filter(p -> p.getId().equals(productInfoResAdmin.getIdcategory())).findFirst()
                    .orElse(null);
            if (tmp != null) {
                productInfoResAdmin.setIdcategory(tmp.getName());
            }

        }
        return list;

    }

    public List<ProductModel> getAllProductsByAdmin() throws ExecutionException, InterruptedException {
        return getProductCollection().orderBy("name").get().get().toObjects(ProductModel.class);

    }

    public ProductModel putProduct(ProductsInfoChange productmodel) throws InterruptedException, ExecutionException {
        ProductModel product = getDocumentById("Products", productmodel.getId()).get().get()
                .toObject(ProductModel.class);

        List<ProductModel> product2 = getFirebase().collection("Category")
                .whereEqualTo("name", productmodel.getIdcategory()).get().get().toObjects(ProductModel.class);
        product.setIdcategory(product2.get(0).getId());

        product.changeProduct(productmodel);
        getProductDocumentById(productmodel.getId()).set(product);
        return product.changeProduct(productmodel);
    }

    public ProductModel deleteProduct(String id) throws InterruptedException, ExecutionException {
        ProductModel product;
        product = getDocumentById("Products", id).get().get().toObject(ProductModel.class);
        deleteDocument("Products", id);
        return product;

    }

    public List<ProductInfoResAdmin> getAllProductsByNameCategory(String nameCate)
            throws ExecutionException, InterruptedException {
        List<CategoryModel> category = getFirebase().collection("Category").whereEqualTo("name", nameCate).get().get()
                .toObjects(CategoryModel.class);
        String id = category.get(0).getId();
        // láy id => lấy product
        List<ProductInfoResAdmin> products = getProductCollection().whereEqualTo("idcategory", id).get().get()
                .toObjects(ProductInfoResAdmin.class);
        return products;

    }

    public ApiResponseData<List<ProductInfoResAdmin>> searchProduct(SearchReq searchReq)
            throws ExecutionException, InterruptedException {
        List<ProductInfoResAdmin> products = getProductCollection().get().get().toObjects(ProductInfoResAdmin.class);
        List<CategoryModel> categories = getFirebase().collection("Category").get().get()
                .toObjects(CategoryModel.class);

        List<ProductInfoResAdmin> myList = new ArrayList<>();

        switch (searchReq.getField()) {
            default:
                break;

            case "name":
                products.forEach((product) -> {
                    if (product.getName().toLowerCase().contains(searchReq.getValue().toLowerCase())) {
                        // try {
                        // product.setReviewPoint(getPointProduct(product));
                        // product.setIdcategory(getCategoryProduct(product.getIdcategory()));
                        // } catch (ExecutionException e) {
                        // e.printStackTrace();
                        // } catch (InterruptedException e) {
                        // e.printStackTrace();
                        // }

                        myList.add(product);
                    }
                });
                break;
            case "category":
                List<CategoryModel> myListCategories = new ArrayList<>();
                categories.forEach(category -> {
                    if (category.getName().toLowerCase().contains(searchReq.getValue().toLowerCase())) {
                        myListCategories.add(category);
                    }
                });
                myListCategories.forEach(category -> {
                    try {
                        getAllProductsByNameCategory(category.getName()).forEach(product -> {
                            // try {
                            // product.setReviewPoint(getPointProduct(product));
                            // product.setIdcategory(getCategoryProduct(product.getIdcategory()));
                            // } catch (ExecutionException e) {
                            // e.printStackTrace();
                            // } catch (InterruptedException e) {
                            // e.printStackTrace();
                            // }

                            myList.add(product);
                        });
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "writer":
                products.forEach((product) -> {
                    if (product.getWriter().contains(searchReq.getValue().toLowerCase())) {
                        // try {
                        // product.setReviewPoint(getPointProduct(product));
                        // product.setIdcategory(getCategoryProduct(product.getIdcategory()));
                        // } catch (ExecutionException e) {
                        // e.printStackTrace();
                        // } catch (InterruptedException e) {
                        // e.printStackTrace();
                        // }

                        myList.add(product);
                    }
                });
                break;
        }

        return new ApiResponseData<>(myList);
    }

    public ApiResponseData<List<ProductInfoRes>> searchProductsByUser(SearchReq searchReq)
            throws ExecutionException, InterruptedException {
        List<ProductInfoRes> productInfoResList = new ArrayList<>();
        List<ProductInfoResAdmin> productModels = searchProduct(searchReq).getData();
        if (!productModels.isEmpty()) {
            productModels.forEach(productModel -> {
                ProductInfoRes productInfoRes = new ProductInfoRes(productModel);
                productInfoResList.add(productInfoRes);
            });
        }

        var categoryIds = productInfoResList.stream().map(p -> p.getIdcategory()).collect(Collectors.toList());
        var categories = getCategoryProduct(categoryIds);
        var reviewProductsIds = productInfoResList.stream().map(p -> p.getId()).collect(Collectors.toList());
        var reviews = getPointProduct(reviewProductsIds);

        for (ProductInfoRes product : productInfoResList) {

            var tmp = categories.stream().filter(p -> p.getId().equals(product.getIdcategory())).findFirst()
                    .orElse(null);
            if (tmp != null) {
                product.setIdcategory(tmp.getName());
            }
            List<ReviewModel> tmp2 = reviews.stream().filter(p -> p.getId().equals(product.getId()))
                    .collect(Collectors.toList());

            if (!tmp2.isEmpty()) {

                float avgPoint = 0;
                for (ReviewModel reviewModel : tmp2) {
                    avgPoint = avgPoint + reviewModel.getReviewPoint();
                }
                product.setReviewPoint(avgPoint);

            }

        }

        return new ApiResponseData<>(productInfoResList);
    }

    public ApiResponseData<List<ProductInfoResAdmin>> paginate(PaginateReq paginateReq)
            throws ExecutionException, InterruptedException {

        if (paginateReq.getPage() < 1) {
            return new ApiResponseData<>(false, "page phải từ 1 ");
        }
        if (paginateReq.getLimit() < 1) {
            return new ApiResponseData<>(false, "limit phải từ 1 ");
        }

        SearchReq searchReq = new SearchReq(paginateReq.getField(), paginateReq.getValue());
        List<ProductInfoResAdmin> productModels = searchProduct(searchReq).getData();
        if (paginateReq.isOptionSort()) {
            Collections.sort(productModels, (p1, p2) -> p2.getDiscount() - p1.getDiscount());
        } else {
            Collections.sort(productModels, (p1, p2) -> p1.getDiscount() - p2.getDiscount());
        }

        List<ProductInfoResAdmin> list = new ArrayList<>();
        try {
            list = productModels.subList((paginateReq.getPage() - 1) * paginateReq.getLimit(), paginateReq.getLimit());

        } catch (Exception e) {
            list = productModels;
        }
        var categoryIds = list.stream().map(p -> p.getIdcategory()).collect(Collectors.toList());
        var categories = getCategoryProduct(categoryIds);
        var reviewProductsIds = list.stream().map(p -> p.getId()).collect(Collectors.toList());
        var reviews = getPointProduct(reviewProductsIds);

        for (ProductInfoResAdmin product : list) {

            var tmp = categories.stream().filter(p -> p.getId().equals(product.getIdcategory())).findFirst()
                    .orElse(null);
            if (tmp != null) {
                product.setIdcategory(tmp.getName());
            }
            List<ReviewModel> tmp2 = reviews.stream().filter(p -> p.getId().equals(product.getId()))
                    .collect(Collectors.toList());

            if (!tmp2.isEmpty()) {

                float avgPoint = 0;
                for (ReviewModel reviewModel : tmp2) {
                    avgPoint = avgPoint + reviewModel.getReviewPoint();
                }
                product.setReviewPoint(avgPoint);

            }

        }
        return new ApiResponseData<>(list);

    }

    public float getPointProduct(String id) throws ExecutionException, InterruptedException {
        List<ReviewModel> reviewModels = getFirebase().collection("Review").whereEqualTo("idProduct", id).get().get()
                .toObjects(ReviewModel.class);
        if (reviewModels.isEmpty()) {
            return -1;
        }
        float avgPoint = 0;
        for (ReviewModel reviewModel : reviewModels) {
            avgPoint = avgPoint + reviewModel.getReviewPoint();
        }
        avgPoint = avgPoint / reviewModels.size();
        return avgPoint;
    }

    public String getCategoryProduct(String idCategory) throws ExecutionException, InterruptedException {
        CategoryModel categoryModel = getFirebase().collection("Category").document(idCategory).get().get()
                .toObject(CategoryModel.class);
        return categoryModel.getName();

    }

    public List<ReviewModel> getPointProduct(List<String> idsProucts) throws ExecutionException, InterruptedException {
        List<String> list = new ArrayList<>();
        try {
            list = idsProucts.subList(0,8);

        } catch (Exception e) {
            list = idsProucts;
        }
     

        List<ReviewModel> reviewModels = getFirebase().collection("Review").whereIn("idProduct", list).get().get()
                .toObjects(ReviewModel.class);
        return reviewModels;

    }

    public List<CategoryModel> getCategoryProduct(List<String> idsCategory)
            throws ExecutionException, InterruptedException {
        List<String> list = idsCategory.stream().distinct().collect(Collectors.toList());
        List<CategoryModel> categories = getFirebase().collection("Category").whereIn("id", list).get().get()
                .toObjects(CategoryModel.class);
        return categories;

    }

    // public ApiResponseData<ProductInfoRes> getProductByIdCategory(String
    // idCategory)
    // throws InterruptedException, ExecutionException {
    // List<ProductInfoResAdmin> list =
    // getProductCollection().whereEqualTo("idcategory",
    // idCategory).get().get().toObjects(
    // ProductInfoResAdmin.class);
    // for (ProductInfoResAdmin ProductInfoResAdmin : list){
    // ProductInfoRes
    // productInfoRes.setReviewPoint(getPointProduct(productInfoResAdmin));
    // }
    // return null;
    // }

}