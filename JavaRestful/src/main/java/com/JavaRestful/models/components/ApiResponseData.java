package com.JavaRestful.models.components;

import org.springframework.stereotype.Component;

@Component
public class ApiResponseData<T> {

  private boolean success;
  private String  message;
  private T data ;

  public ApiResponseData(){
    this.success =true;
    this.message = "Success";
    this.data =null ;
  }
  public ApiResponseData(boolean success , String message ){
    this.success = success;
    this.message = message;

  }
  public ApiResponseData(T data){
    this.success =true;
    this.message = "Success";
    this.data = data;
  }


  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }


}
