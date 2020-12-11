package com.JavaRestful.controllers;

import com.JavaRestful.models.requests.bill.BillOrderReq;
import com.JavaRestful.models.requests.bill.PaginateBillReq;
import com.JavaRestful.models.requests.bill.PutStatusBill;
import com.JavaRestful.models.response.bill.BillRes;
import org.springframework.web.bind.annotation.*;

import com.JavaRestful.models.components.ApiResponseData;
import com.JavaRestful.services.BillService;
import com.JavaRestful.models.components.BillModel;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController

public class BillController extends ControllerBridge {
    private final BillService billservice;

    public BillController() {
        this.billservice = new BillService();

    }

    @GetMapping("admin/bills")
    // add author
    public ApiResponseData<List<BillModel>> getAllBill() {
        try {
            return new ApiResponseData<>(this.billservice.getAllBill());
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @GetMapping("/bill")
    public ApiResponseData<BillRes> getBillById(@RequestParam String id)
            throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(new BillRes(this.billservice.getBillById(id)));
    }

    @GetMapping("/admin/bill")
    public ApiResponseData<BillModel> getBillByIdByAdmin(@RequestParam String id)
            throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(this.billservice.getBillById(id));
    }

    @GetMapping("/bill/user")
    public ApiResponseData<List<BillModel>> getBillByUser(@RequestParam String user)
            throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(this.billservice.getBillByUser(user));
    }

    @GetMapping("/bill/code")
    public ApiResponseData<BillRes> getBillByCode(@RequestParam String code)
            throws ExecutionException, InterruptedException {
        return new ApiResponseData<>(new BillRes(this.billservice.getBillByCode(code)));
    }

    @PostMapping("/bills")
    public @ResponseBody ApiResponseData<BillRes> addBill(@RequestBody BillOrderReq bill)
            throws ExecutionException, InterruptedException {
        // add author

        return this.billservice.addBill(bill);
    }

    @PutMapping("admin/bill")
    public ApiResponseData<String> putStatus(@RequestBody PutStatusBill putStatusBill)
            throws ExecutionException, InterruptedException {
        return this.billservice.putStatusBill(putStatusBill);
    }

    @DeleteMapping("admin/bills")
    public ApiResponseData<BillModel> deleteBill(@RequestParam String id) {
        try {
            return new ApiResponseData<>(new BillModel(this.billservice.deleteBill(id)));
        } catch (Exception e) {
            return new ApiResponseData<>(false, "Lỗi");
        }
    }

    @GetMapping("admin/paginateBill")
    public ApiResponseData<List<BillModel>> paginateBill(@RequestParam (defaultValue = "nameUser") String field, @RequestParam (required = false, defaultValue = "" ) String value,
            @RequestParam String dateBegin, @RequestParam String dateEnd, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit)
            throws ExecutionException, InterruptedException, ParseException {
        PaginateBillReq  paginateBillReq = new PaginateBillReq(field, value, dateBegin , dateEnd , page , limit);
        return this.billservice.paginate(paginateBillReq);
    }



    
}
