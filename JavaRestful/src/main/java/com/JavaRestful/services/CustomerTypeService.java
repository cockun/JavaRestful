package com.JavaRestful.services;

import com.JavaRestful.models.components.AccountModel;
import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.models.components.CustomerTypeModel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.List;

public class CustomerTypeService extends  ServiceBridge{

    public CollectionReference getCustomerCollection (){
        return getFirebase().collection("CustomerType");
    }
    public DocumentReference getCustomerDocumentById(String id )   {
        return getDocumentById("CustomerType",id);
    }

    public ApiResponseData<CustomerTypeModel> addCustomerType(CustomerTypeModel customerTypeModel){
        customerTypeModel.setId(randomDocumentId("CustomerType"));
        getCustomerDocumentById(customerTypeModel.getId()).set(customerTypeModel);
        return new ApiResponseData<>(customerTypeModel);
    }
    public ApiResponseData<String> deleteCustomerType(String id ){
        try{
            if(id.equals("gVvtdrp0rFXr7i6OxJqG"))   {
                return new ApiResponseData<>(false,"Không được xóa type này");
            }else {
                deleteDocument("CustomerType",id);
                return new ApiResponseData<>("success");
            }
        }catch (Exception e) {
            return  new ApiResponseData<>(false,e.getMessage());
        }
    }

    public ApiResponseData<String> putCustomer(CustomerTypeModel customerTypeModel){
        getCustomerDocumentById(customerTypeModel.getId()).set(customerTypeModel);
        return new ApiResponseData<>("Success");
    }





}
