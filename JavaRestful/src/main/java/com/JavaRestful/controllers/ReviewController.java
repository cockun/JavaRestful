package com.JavaRestful.controllers;


import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.ReviewModel;
import com.JavaRestful.models.requests.review.PutReview;
import com.JavaRestful.models.response.review.ReviewResponse;
import com.JavaRestful.services.ReviewService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ReviewController extends ControllerBridge{
    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }
    @PostMapping("/review")
    public ApiResponseData<String> reviewProduct(@RequestBody ReviewModel reviewModel) throws ExecutionException, InterruptedException {
        return this.reviewService.reviewProduct(reviewModel);
    }
    @PutMapping("/review")
    public ApiResponseData<String> putReview(@RequestBody PutReview putReview) throws ExecutionException, InterruptedException {
        return this.reviewService.putReview(putReview);
    }
    @GetMapping("/review")
    public ApiResponseData<List<ReviewResponse>> getReview(@RequestParam String field , @RequestParam String value) throws ExecutionException, InterruptedException {
        return this.reviewService.getReviewByField(field,value);
    }



}
