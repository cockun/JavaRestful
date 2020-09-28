package com.JavaRestful.services;

import com.JavaRestful.models.components.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.JavaRestful.models.requests.bill.BillOrderReq;
import com.JavaRestful.models.requests.bill.BillOrderReqInfo;
import com.JavaRestful.models.requests.bill.PutStatusBill;
import com.JavaRestful.models.response.bill.BillRes;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

public class BillService extends ServiceBridge {
    public CollectionReference getBillCollection (){

        return getFirebase().collection("Bill");
    }

    public DocumentReference getBillDocumentById(String id )   {
        return getDocumentById("Bill",id);
    }


    public List<BillModel> getBillByUser(String user) throws ExecutionException, InterruptedException {

            return getBillCollection().whereEqualTo("nameUser",user).get().get().toObjects((BillModel.class));

    }

    public BillModel getBillById(String id ) throws ExecutionException, InterruptedException {
        return getBillDocumentById(id).get().get().toObject(BillModel.class);
    }
    public BillModel getBillByCode(String code ) throws ExecutionException, InterruptedException {
        return getBillCollection().whereEqualTo("code" ,code).get().get().toObjects(BillModel.class).get(0);
    }





    public BillModel deleteBill(String id) throws ExecutionException, InterruptedException {
        BillModel billmodel;
        billmodel = getDocumentById("Bill",id).get().get().toObject(BillModel.class);
        deleteDocument("Bill",id);
        return billmodel;
    }

    public  List<BillModel> getAllBill() throws ExecutionException, InterruptedException {
        return getBillCollection().get().get().toObjects(BillModel.class);

    }

    public double getMoney() throws ExecutionException, InterruptedException {
        double res = 0;
        List<BillModel> billModel = getBillCollection().get().get().toObjects(BillModel.class);
        for (int i = 0 ; i < billModel.size() ; i++){
            res += billModel.get(i).getTotal();
        }
        return res;
    }

    public BillInfoModel createBillInfo(ProductModel productModel , BillOrderReqInfo billOrderReq){
        BillInfoModel billInfoModel = new BillInfoModel();

        billInfoModel.setCode(productModel.getCode());
        billInfoModel.setDiscount(productModel.getDiscount());
        billInfoModel.setIdProduct(productModel.getId());
        billInfoModel.setNameProduct(productModel.getName());
        billInfoModel.setPrice(productModel.getPrice());
        billInfoModel.setPriceRoot(productModel.getRootprice());

        billInfoModel.setQuantity(billOrderReq.getQuantity());


        billInfoModel.setTotal(productModel.getDiscount()*billInfoModel.getQuantity());

        return billInfoModel;
    }


    public ApiResponseData<BillRes>  addBill(BillOrderReq billOrderReq) throws ExecutionException, InterruptedException {

        ArrayList<BillInfoModel> billOrderReqInfoArray = new ArrayList<>();
        long total  = 0 ;
        for(BillOrderReqInfo billOrderReqInfo : billOrderReq.getBillOrderReqInfos()){
            ProductService productService = new ProductService();
            BillInfoModel billInfoModel = createBillInfo(productService.getProductByIdAdmin(billOrderReqInfo.getIdProduct()),billOrderReqInfo);
            billOrderReqInfoArray.add(billInfoModel);
            total = total + billInfoModel.getTotal();
        }

        long discount = 0 ;
        if(billOrderReq.getPromotionCode() !=""){
            PromotionService promotionService = new PromotionService();
            PromotionModel promotionModel = promotionService.getPromotionByCode(billOrderReq.getPromotionCode());
            if(promotionModel == null){
                return new ApiResponseData<>(false,"Mã giảm giá không tồn tại");
            }
            if(promotionModel.isPromotionCategory()){
                discount = total*promotionModel.getDiscount()/100;
            }else{
                discount = promotionModel.getDiscount();
            }

        }

        BillModel billModel = new BillModel(billOrderReq,billOrderReqInfoArray,total-discount);
        billModel.setDiscount(discount);
        billModel.setId(randomDocumentId("Bill"));
        billModel.setCode(HelpUtility.getRandomCode("BL"));
        billModel.setDate(java.time.LocalDate.now().toString());
        getBillDocumentById(billModel.getId()).set(billModel);

        //add bill vào income
        IncomeModel incomeModel = new IncomeModel();
        incomeModel.setCost(total-discount);
        incomeModel.setIdIncome(billModel.getId());

        incomeModel.setId(randomDocumentId("Imcomes"));
        getDocumentById("InComes",incomeModel.getId()).set(incomeModel);

        return new ApiResponseData<>(new BillRes(billModel));

    }

    public ApiResponseData<String> putStatusBill(PutStatusBill putStatusBill) throws ExecutionException, InterruptedException {
        getBillDocumentById(putStatusBill.getId()).set(putStatusBill.isPay());

        IncomeModel incomeModel = getFirebase().collection("Imcomes").whereEqualTo("idIncome",putStatusBill.getId()).get().get().toObjects(IncomeModel.class).get(0);
        getDocumentById("Incomes",incomeModel.getId()).set(incomeModel);
        return new ApiResponseData<>("");
    }











}
