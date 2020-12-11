package com.JavaRestful.services;

import com.JavaRestful.models.components.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.JavaRestful.models.requests.bill.BillOrderReq;
import com.JavaRestful.models.requests.bill.BillOrderReqInfo;
import com.JavaRestful.models.requests.bill.PaginateBillReq;
import com.JavaRestful.models.requests.bill.PutStatusBill;
import com.JavaRestful.models.response.bill.BillRes;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import javassist.bytecode.stackmap.BasicBlock.Catch;

public class BillService extends ServiceBridge {

    public CollectionReference getBillCollection() {

        return getFirebase().collection("Bill");
    }

    public DocumentReference getBillDocumentById(String id) {
        return getDocumentById("Bill", id);
    }

    public List<BillModel> getBillByUser(String user) throws ExecutionException, InterruptedException {

        return getBillCollection().whereEqualTo("nameUser", user).get().get().toObjects((BillModel.class));

    }

    public BillModel getBillById(String id) throws ExecutionException, InterruptedException {
        return getBillDocumentById(id).get().get().toObject(BillModel.class);
    }

    public BillModel getBillByCode(String code) throws ExecutionException, InterruptedException {
        return getBillCollection().whereEqualTo("code", code).get().get().toObjects(BillModel.class).get(0);
    }

    public BillModel deleteBill(String id) throws ExecutionException, InterruptedException {
        StorageService storageService = new StorageService();
        BillModel billmodel;
        billmodel = getDocumentById("Bill", id).get().get().toObject(BillModel.class);
        for (BillInfoModel billInfoModel : billmodel.getBillInfoModel()) {
            StorageModel storageModel = storageService.getStorageByIdProduct(billInfoModel.getIdProduct()).get(0);
            storageModel.setQuantity(storageModel.getQuantity() + billInfoModel.getQuantity());
            storageService.getStorageDocumentById(storageModel.getId()).set(storageModel);
        }
        IncomeService incomeService = new IncomeService();
        incomeService.deleteIncomeByIdBill(id);
        deleteDocument("Bill", id);
        return billmodel;
    }

    public List<BillModel> getAllBill() throws ExecutionException, InterruptedException {
        List<BillModel>  listRes = getBillCollection().get().get().toObjects(BillModel.class);
        Collections.sort(listRes,(p1,p2)->{
            try {
                return HelpUtility.convertStringToDate(p2.getDate())
                        .compareTo(HelpUtility.convertStringToDate(p1.getDate()));
            } catch (ParseException e) {
                return -1;
            }
        });
        return listRes ;

    }

    public BillInfoModel createBillInfo(ProductModel productModel, BillOrderReqInfo billOrderReq) {
        BillInfoModel billInfoModel = new BillInfoModel();

        billInfoModel.setCode(HelpUtility.getRandomCode("BLIF"));
        billInfoModel.setDiscount(productModel.getDiscount());
        billInfoModel.setIdProduct(productModel.getId());
        billInfoModel.setNameProduct(productModel.getName());
        billInfoModel.setPrice(productModel.getPrice());
        billInfoModel.setPriceRoot(productModel.getRootprice());
        billInfoModel.setQuantity(billOrderReq.getQuantity());

        billInfoModel.setTotal(productModel.getDiscount() * billInfoModel.getQuantity());

        return billInfoModel;
    }

    public ApiResponseData<BillRes> addBill(BillOrderReq billOrderReq) throws ExecutionException, InterruptedException {
        if(!HelpUtility.validEmail(billOrderReq.getAddress())){
            return new ApiResponseData<>(false,"email không đúng");
        }
        if(!HelpUtility.validPhone(billOrderReq.getPhone())){
            return new ApiResponseData<>(false,"Số điện thoại không đúng");
        }
        if (billOrderReq.getAddress() == "" || billOrderReq.getPhone() == "" || billOrderReq.getEmail() == "") {
            return new ApiResponseData<>(false, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            StorageService storageService = new StorageService();
            ArrayList<BillInfoModel> billOrderReqInfoArray = new ArrayList<>();
            long total = 0;
            for (BillOrderReqInfo billOrderReqInfo : billOrderReq.getBillOrderReqInfos()) {

                BillInfoModel billInfoModel = createBillInfo(getFirebase().collection("Products")
                        .document(billOrderReqInfo.getIdProduct()).get().get().toObject(ProductModel.class),
                        billOrderReqInfo);
                // check Storage
                if (storageService.getStorageByIdProduct(billInfoModel.getIdProduct()).isEmpty()) {
                    return new ApiResponseData<>(false, "sản phẩm không còn trong kho");
                }
                if (billInfoModel.getQuantity() > storageService.getStorageByIdProduct(billInfoModel.getIdProduct())
                        .get(0).getQuantity()) {
                    return new ApiResponseData<>(false,
                            billOrderReqInfo.getIdProduct() + " Không còn đủ hàng trong kho");
                }
                billOrderReqInfoArray.add(billInfoModel);
                total = total + billInfoModel.getTotal();
            }

            long discount = 0;
            if (billOrderReq.getPromotionCode() != "" && billOrderReq.getPromotionCode() != null) {
                try {
                    PromotionService promotionService = new PromotionService();
                    PromotionModel promotionModel = promotionService
                            .getPromotionByCode(billOrderReq.getPromotionCode());
                    if (promotionModel == null) {
                        return new ApiResponseData<>(false, "Mã giảm giá không tồn tại");
                    }
                    if (promotionModel.isPromotionCategory()) {
                        discount = total * promotionModel.getDiscount() / 100;
                    } else {
                        discount = promotionModel.getDiscount();
                    }
                } catch (Exception e) {
                    new ApiResponseData<>(false, e.getMessage());
                }

            }

            BillModel billModel = new BillModel(billOrderReq, billOrderReqInfoArray, total - discount);
            billModel.setDiscount(discount);
            billModel.setCodePromotion(billOrderReq.getPromotionCode());
            billModel.setId(randomDocumentId("Bill"));
            billModel.setCode(HelpUtility.getRandomCode("BL"));
            billModel.setDate(java.time.LocalDate.now().toString());
            billModel.setAddress(billOrderReq.getAddress());
            billModel.setPhone(billOrderReq.getPhone());
            billModel.setEmail(billOrderReq.getEmail());

            if (billOrderReq.isUsePoint()) {
                RewardPointModel rewardPointModel = getRewardPoint(billModel.getNameUser());
                total = (long) (total - rewardPointModel.getPointAvailable());
                billModel.setTotal(total);
                rewardPointModel.setPointAvailable(0);
                getFirebase().collection("Accounts").document(rewardPointModel.getIdAccount()).collection("RewardPoint")
                        .document(getIdReward(rewardPointModel.getIdAccount())).set(rewardPointModel);

            }

            getBillDocumentById(billModel.getId()).set(billModel);

            // storage
            for (BillInfoModel billInfoModel : billModel.getBillInfoModel()) {
                StorageModel storageModel = storageService.getStorageByIdProduct(billInfoModel.getIdProduct()).get(0);
                storageModel.setQuantity(storageModel.getQuantity() - billInfoModel.getQuantity());
                storageService.getStorageDocumentById(storageModel.getId()).set(storageModel);
            }
            // income

            IncomeModel incomeModel = new IncomeModel();
            incomeModel.setCost(total - discount);
            incomeModel.setIdIncome(billModel.getId());
            incomeModel.setDate(java.time.LocalDate.now().toString());
            incomeModel.setId(randomDocumentId("Incomes"));
            getDocumentById("Incomes", incomeModel.getId()).set(incomeModel);
            return new ApiResponseData<>(new BillRes(billModel));
        }

    }

    public ApiResponseData<String> putStatusBill(PutStatusBill putStatusBill)
            throws ExecutionException, InterruptedException {
        BillModel billModel = getBillById(putStatusBill.getId());
        if (putStatusBill.isPay() == billModel.getPay()) {
            return new ApiResponseData<>(false, "Bill đã ở trạng thái này");
        }
        billModel.setPay(putStatusBill.isPay());

        // set point

        List<CustomerTypeModel> list = getFirebase().collection("CustomerType").orderBy("value").get().get()
                .toObjects(CustomerTypeModel.class);

        RewardPointModel rewardPointModel = getRewardPoint(billModel.getNameUser());
        if (putStatusBill.isPay()) {

            rewardPointModel.setPointRank(rewardPointModel.getPointRank() + billModel.getTotal() * 2 / 100);
            rewardPointModel.setPointAvailable(rewardPointModel.getPointAvailable() + billModel.getTotal() * 2 / 100);
        } else {
            rewardPointModel.setPointRank(rewardPointModel.getPointRank() - billModel.getTotal() * 2 / 100);
            rewardPointModel.setPointAvailable(rewardPointModel.getPointAvailable() - billModel.getTotal() * 2 / 100);
        }
        AccountModel accountModel = getFirebase().collection("Accounts").whereEqualTo("user", billModel.getNameUser())
                .get().get().toObjects(AccountModel.class).get(0);
        for (CustomerTypeModel ponit : list) {
            if (rewardPointModel.getPointRank() > ponit.getValue()) {
                accountModel.setIdCustomer(ponit.getId());
            }
        }

        // getIdReward(rewardPointModel);
        // add income
        IncomeModel incomeModel = getFirebase().collection("Incomes").whereEqualTo("idIncome", putStatusBill.getId())
                .get().get().toObjects(IncomeModel.class).get(0);
        incomeModel.setStatus(putStatusBill.isPay());

        getBillDocumentById(putStatusBill.getId()).set(billModel);
        getFirebase().collection("Accounts").document(accountModel.getId()).set(accountModel);
        getDocumentById("Incomes", incomeModel.getId()).set(incomeModel);
        getFirebase().collection("Accounts").document(rewardPointModel.getIdAccount()).collection("RewardPoint")
                .document(getIdReward(rewardPointModel.getIdAccount())).set(rewardPointModel);
        return new ApiResponseData<>("success");
    }

    public RewardPointModel getRewardPoint(String user) throws ExecutionException, InterruptedException {
        AccountModel accountModel = getFirebase().collection("Accounts").whereEqualTo("user", user).get().get()
                .toObjects(AccountModel.class).get(0);
        RewardPointModel rewardPointModel = getFirebase().collection("Accounts").document(accountModel.getId())
                .collection("RewardPoint").get().get().toObjects(RewardPointModel.class).get(0);
        return rewardPointModel;
    }

    public String getIdReward(String idAccount) throws ExecutionException, InterruptedException {
        return getFirebase().collection("Accounts").document(idAccount).collection("RewardPoint")
                .whereEqualTo("idAccount", idAccount).get().get().getDocuments().get(0).getId();

    }

    public ApiResponseData<List<BillModel>> paginate(PaginateBillReq paginateBillReq)
            throws ExecutionException, InterruptedException, ParseException {
        if (paginateBillReq.getPage() < 1) {
            return new ApiResponseData<>(false, "page phải từ 1 ");
        }
        if (paginateBillReq.getLimit() < 1) {
            return new ApiResponseData<>(false, "limit phải từ 1 ");
        }
        List<BillModel> lBillModels = searchBill(paginateBillReq.getField(), paginateBillReq.getValue(),
                paginateBillReq.getDateBegin(), paginateBillReq.getDateEnd());
        List<BillModel> listRes;
        try {
            listRes = lBillModels.subList((paginateBillReq.getPage() - 1) * paginateBillReq.getLimit(), paginateBillReq.getLimit());

        } catch (Exception e) {
            listRes = lBillModels;
        }
        Collections.sort(listRes,(p1,p2)->{
            try {
                return HelpUtility.convertStringToDate(p2.getDate())
                        .compareTo(HelpUtility.convertStringToDate(p1.getDate()));
            } catch (ParseException e) {
                return -1;
            }
        });
         
        return new ApiResponseData<>(listRes);
    }

    public List<BillModel> searchBill(String field, String value, String dateBegin, String dateEnd)
            throws ExecutionException, InterruptedException, ParseException {
        List<BillModel> listBillModels = getAllBill();
        List<BillModel> lBillModelsRes;
        Date dateBegin2 = HelpUtility.convertStringToDate(dateBegin);
        Date dateEnd2 = HelpUtility.convertStringToDate(dateEnd);

        switch (field) {

            case "nameUser":
                lBillModelsRes = listBillModels.stream()
                        .filter(p -> p.getNameUser().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());
                lBillModelsRes = lBillModelsRes.stream().filter(p -> {
                    try {
                        return (HelpUtility.convertStringToDate(p.getDate()).compareTo(dateBegin2) >= 0
                                && HelpUtility.convertStringToDate(p.getDate()).compareTo(dateEnd2) <= 0);
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
                break;

            default:
                return null;
        }

        return lBillModelsRes;
    }

}
