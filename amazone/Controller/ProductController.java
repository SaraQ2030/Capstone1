package org.example.amazone.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Api.ApiMessage;
import org.example.amazone.Model.Category;
import org.example.amazone.Model.Product;
import org.example.amazone.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazone/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> products=productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiMessage("Add successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id,@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= productService.updateProduct(id,product);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiMessage("product updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiMessage("product Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("Cannot find id "));
    }

    @GetMapping("/filter/{min}/{max}")
    public ResponseEntity filter(@PathVariable int min ,@PathVariable int max){
        ArrayList<Product>list=productService.filterPrice(min,max);
        if (list.isEmpty()){
            return ResponseEntity.status(400).body(new ApiMessage("empty list "));
        }
        return ResponseEntity.status(200).body(list);
    }
}
