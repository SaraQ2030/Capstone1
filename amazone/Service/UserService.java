package org.example.amazone.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Model.Merchant;
import org.example.amazone.Model.MerchantStock;
import org.example.amazone.Model.Product;
import org.example.amazone.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;

    @Getter
    ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int id, User user) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                users.set(users.indexOf(u), user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

// 12- user buy a product directly
//user use discount coupone Sara15
    public boolean buyProduct(int userId, int merchantId, int productId,int dis) {
        int discount=1;
        for (User u : users) {
            for (Merchant m : merchantService.getMarche()) {
                for (Product p : productService.getProducts()) {
                    for (MerchantStock ms: merchantStockService.getMerchantStocks()){
                    if (u.getId().equals(userId) && m.getId().equals(merchantId) && p.getId().equals(productId) && u.getBalance() >= p.getPrice() &&  ms.getStock()>0) {
                       if (ms.getProduct_id().equals(p.getId()) && ms.getMerchant_id().equals(m.getId()) ){

                          //use discount
                           if (dis==15 && discount==1){
                               ms.setStock(ms.getStock()-1);
                              double d=p.getPrice()*0.15;
                               u.setBalance(u.getBalance()- d );
                              discount--;
                               return true;
                           }
                           else{
                               ms.setStock(ms.getStock()-1);
                               u.setBalance(u.getBalance()-p.getPrice());
                               return true;
                           }


                       }}}}}}
return false;
    }





//admin delete from merchant - users -product
    public boolean deleteAdmin(int id,int del,int id_del){
        for (User u:users){
            if (u.getId().equals(id) && u.getRole().equalsIgnoreCase("admin")){
                        switch (del)
                        {

                            case 1:
                                for (Merchant m:merchantService.getMarche()){
                                    if (m.getId()==id_del){
                                        merchantService.deleteMerchant(m.getId());
                                        return true;
                                    } break;}
                             case 2:
                                 for (Product p: productService.getProducts()){
                                 if (p.getId().equals(id_del)){
                                 productService.deleteProduct(p.getId());
                                 return true;
                                  }  break;}
                            case 3:
                                for (User user:users){
                                    if (user.getId()==id_del && user.getRole().equalsIgnoreCase("customer")){
                                        users.remove(users.indexOf(user));
                                        return true;
                                    }  break;}



                            }
                        }

                    }
        return false;
            }




    public ArrayList<Product> addToCart(int userId, int merchantId, int productId,int num) {
        ArrayList<Product> list=new ArrayList<>();
        while (num!=0) {
            for (User u : users) {
                for (Merchant m : merchantService.getMarche()) {
                    for (Product p : productService.getProducts()) {
                        for (MerchantStock ms : merchantStockService.getMerchantStocks()) {
                            if (u.getId().equals(userId) && m.getId().equals(merchantId) && p.getId().equals(productId) && u.getBalance() >= p.getPrice() && ms.getStock() > 0) {
                                if (ms.getProduct_id().equals(p.getId()) && ms.getMerchant_id().equals(m.getId())) {
                                    list.add(p);
                                    num--;

                                    ms.setStock(ms.getStock() - 1);
                                    u.setBalance(u.getBalance() - p.getPrice());


                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
        }

