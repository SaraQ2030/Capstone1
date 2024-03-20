package org.example.amazone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Api.ApiMessage;
import org.example.amazone.Model.Product;
import org.example.amazone.Model.User;
import org.example.amazone.Service.ProductService;
import org.example.amazone.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazone/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> user=userService.getUsers();
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdd =userService.addUser(user);
        if (isAdd){
            return ResponseEntity.status(200).body(new ApiMessage("Add successfully"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("fail Adding "));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id,@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate= userService.updateUser(id,user);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiMessage("User updated"));
        }
        return ResponseEntity.status(400).body("cannot update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiMessage("user Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("Cannot find id "));
    }

    @PutMapping("/buy/{userId}/{marchantId}/{productId}/{dis}")
    public ResponseEntity buy(@PathVariable int userId,@PathVariable int marchantId,@PathVariable int productId,@PathVariable int dis){
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
        boolean isBuy = userService.buyProduct(userId,marchantId,productId,dis);
        if (isBuy) {
            return ResponseEntity.status(200).body(new ApiMessage("Buy product successfly"));
        }
        return ResponseEntity.status(400).body("cannot buy");
    }
    @DeleteMapping("/delete/{id}/{del}/{id_del}")
    public ResponseEntity adminDelete(@PathVariable int id,@PathVariable int del,@PathVariable int id_del){
        boolean isDeleted=userService.deleteAdmin(id,del,id_del);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiMessage("Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiMessage("Cannot find id "));
    }


    @PutMapping("/cart/{userId}/{marchantId}/{productId}/{num}")
    public ResponseEntity addCart(@PathVariable int userId,@PathVariable int marchantId,@PathVariable int productId,@PathVariable int num){
        ArrayList<Product> cart = userService.addToCart(userId,marchantId,productId,num);
        if (cart.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiMessage("fail to add"));
        }
        return ResponseEntity.status(200).body(cart);
    }
}