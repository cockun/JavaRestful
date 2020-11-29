package com.JavaRestful.services;

import com.JavaRestful.models.components.*;
import com.JavaRestful.models.requests.review.PutReview;
import com.JavaRestful.models.response.review.ReviewResponse;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

public class ReviewService extends ServiceBridge {
    private final BillService billService;
    public ReviewService(){
        this.billService = new BillService();
    }
    public List<ReviewModel> getReviewByIdBill(String idBill) throws ExecutionException, InterruptedException {
        return getReviewCollection().whereEqualTo("idBill",idBill).get().get().toObjects(ReviewModel.class);
    }
    public CollectionReference getReviewCollection (){
        return getFirebase().collection("Review");
    }
    public DocumentReference getReviewDocumentById(String id )   {
        return getDocumentById("Review",id);
    }
    public ReviewModel getReviewById(String id) throws ExecutionException, InterruptedException {
        return getReviewDocumentById(id).get().get().toObject(ReviewModel.class);
    }
    public Boolean validReview(int point ){
        if(point < 1 || point>5){
            return false;
        }else {
            return true;
        }
    }
    public ApiResponseData<String> putReview(PutReview putReview) throws ExecutionException, InterruptedException {
        if(!validReview(putReview.getReviewPoint())){
            return new ApiResponseData<>(false,"Đánh giá chỉ từ 1 đến 5 sao");
        }
        ReviewModel reviewModel = getReviewById(putReview.getId()) ;
        if(reviewModel!=null){
            if(reviewModel.getIdUser().equals(putReview.getIdUser())){
                getReviewDocumentById(putReview.getId()).set(putReview);
                return  new ApiResponseData<>("Success");
            }else {
                return new ApiResponseData<>(false,"Tài khoản lỗi");
            }

        }else {
            return new ApiResponseData<>(false,"Đánh giá không tồn tại");
        }


    }


    public ApiResponseData<String> reviewProduct(ReviewModel reviewModel) throws ExecutionException, InterruptedException {
        if(!validReview(reviewModel.getReviewPoint())){
            return new ApiResponseData<>(false,"Đánh giá chỉ từ 1 đến 5 sao");
        }
        List<ReviewModel> listReview = getReviewByIdBill(reviewModel.getIdBill());
        if(!listReview.isEmpty()){
            for (ReviewModel review : listReview ){
                if(review.getIdProduct().equals(reviewModel.getIdProduct())){
                    return new ApiResponseData<>(false,"Sản phẩm đã được đánh giá");
                }
            }
        }

        BillModel bill = this.billService.getBillById(reviewModel.getIdBill());
        if(bill!=null){
            AccountModel accountModel = getFirebase().collection("Accounts").document(reviewModel.getIdUser()).get().get().toObject(AccountModel.class);
            if(accountModel != null){
                if(bill.getNameUser().equals(accountModel.getUser())){
                    for (BillInfoModel billInfoModel : bill.getBillInfoModel()){
                        if(billInfoModel.getIdProduct().equals(reviewModel.getIdProduct())){
                            reviewModel.setId(randomDocumentId("Review"));
                            getReviewDocumentById(reviewModel.getId()).set(reviewModel);
                            return new ApiResponseData<>("Success");
                        }
                    }
                    return new ApiResponseData<>(false,"Sản phẩm  lỗi");
                }else {
                    return new ApiResponseData<>(false,"Tài khoản lỗi");
                }

            }else{
                return new ApiResponseData<>(false,"Tài không tồn tại");
            }


        }else {
            return new ApiResponseData<>(false,"Bill không tồn tại");
        }
    }
    public ApiResponseData<List<ReviewResponse>> getReviewByField(String field, String value) throws ExecutionException, InterruptedException {
        List<ReviewModel> reviewModels = getReviewCollection().whereEqualTo(field,value).get().get().toObjects(ReviewModel.class);
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        reviewModels.forEach(reviewModel -> {
            ReviewResponse reviewResponse = new ReviewResponse();
            try {
                reviewResponse.setId(reviewModel.getId());
                reviewResponse.setComment(reviewModel.getComment());
                reviewResponse.setReviewPoint(reviewModel.getReviewPoint());
                reviewResponse.setIdBill(reviewModel.getIdBill());
                AccountModel accountModel = getFirebase().collection("Accounts").document(reviewModel.getIdUser()).get().get().toObject(AccountModel.class);
                reviewResponse.setNameUser(  accountModel.getUser());
                reviewResponse.setNameProduct(getFirebase().collection("Products").document(reviewModel.getIdProduct()).get().get().toObject(ProductModel.class).getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            reviewResponses.add(reviewResponse);

        });
         return new  ApiResponseData<>(reviewResponses);
    }






}
