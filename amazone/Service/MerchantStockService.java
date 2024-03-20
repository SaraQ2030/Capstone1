package org.example.amazone.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantService merchantService;
    private final ProductService productService;

    @Getter
    ArrayList<MerchantStock> merchantStocks=new ArrayList<>();

    public boolean addMerchantStock(MerchantStock merchantStock){
        for (MerchantStock ms:merchantStocks){
            if (ms.getId().equals(merchantStock.getId())){
                return false;
            }
        }

        merchantStocks.add(merchantStock);
return true;
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock){
        for (MerchantStock ms:merchantStocks){
            if (ms.getId().equals(id)){
                merchantStocks.set(merchantStocks.indexOf(ms),merchantStock);
                return true;
            }
        }
        return false;
    }
    public boolean deleteMerchantStock(int id){
        for (MerchantStock ms:merchantStocks){
            if (ms.getId().equals(id)){
                merchantStocks.remove(ms);
                return true;
            }
        }
        return false;
    }
//11-user add to the stock
    public boolean addToStock(int merchant_id,int product_id,int amount){
        for (MerchantStock ms:merchantStocks) {
            if (ms.getMerchant_id()==(merchant_id) && ms.getProduct_id()==product_id) {
                for (Merchant m:merchantService.getMarche()){
                      if (m.getId()== merchant_id){
                          for (Product product: productService.getProducts()){
                              if (product.getId()== product_id){
                                  ms.setStock(ms.getStock() + amount);
                                  return true;
                              }
                          }
                      }
            }
             }
}
             return false;
    }
}
