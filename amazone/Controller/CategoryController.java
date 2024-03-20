package org.example.amazone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Api.ApiMessage;
import org.example.amazone.Model.Category;
import org.example.amazone.Model.Product;
import org.example.amazone.Service.CategoryService;
import org.example.amazone.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazone/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> categories=categoryService.getCategory();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdd =categoryService.addCategory(category);
        if (isAdd){

            return ResponseEntity.status(200).body(new ApiMessage("Add successfully"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("fail Adding "));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable int id,@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= categoryService.updateCategory(id,category);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiMessage("category updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiMessage("category Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("not found id "));
    }
}
