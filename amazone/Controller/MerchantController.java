package org.example.amazone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Api.ApiMessage;
import org.example.amazone.Model.Merchant;
import org.example.amazone.Model.Product;
import org.example.amazone.Service.MerchantService;
import org.example.amazone.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazone/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        ArrayList<Merchant> merchants=merchantService.getMarche();
        return ResponseEntity.status(200).body(merchants);
    }


    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       boolean isAdd= merchantService.addMerchant(merchant);
        if (isAdd){return ResponseEntity.status(200).body(new ApiMessage("Add successfully"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("fail Adding "));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable int id,@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= merchantService.updateMerchant(id,merchant);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiMessage("merchant updated"));
        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiMessage("merchant Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("Cannot found id "));
    }

}
