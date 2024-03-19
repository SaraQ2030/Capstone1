package org.example.amazone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Api.ApiMessage;
import org.example.amazone.Model.Category;
import org.example.amazone.Model.Merchant;
import org.example.amazone.Model.MerchantStock;
import org.example.amazone.Model.Product;
import org.example.amazone.Service.CategoryService;
import org.example.amazone.Service.MerchantService;
import org.example.amazone.Service.MerchantStockService;
import org.example.amazone.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/amazone/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiMessage("Add successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiMessage("merchant stock updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiMessage("merchant stock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("not found id "));
    }


    //controller for add user product to the stock;
    @PutMapping("/refill/{m_id}/{p_id}/{amount}")
    public ResponseEntity addToStock(@PathVariable int m_id,@PathVariable int p_id,@PathVariable int amount){
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
        boolean isRefill = merchantStockService.addToStock(m_id,p_id,amount);
        if (isRefill) {
            return ResponseEntity.status(200).body(new ApiMessage("stock Refilled "));
        }
        return ResponseEntity.status(400).body("cannot refill the stock");
    }
}
