package org.example.amazone.Service;

import org.example.amazone.Model.Merchant;
import org.example.amazone.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantService {
    ArrayList<Merchant> merchants=new ArrayList<>();
    public ArrayList<Merchant> getMarche(){
        return merchants;
    }
    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public boolean updateMerchant(int id, Merchant merchant){
        for (Merchant m:merchants){
            if (m.getId().equals(id)){
                merchants.set(merchants.indexOf(m),merchant );
                return true;
            }
        }
        return false;
    }
    public boolean deleteMerchant(int id){
        for (Merchant m:merchants){
            if (m.getId().equals(id)){
                merchants.remove(m);
                return true;
            }
        }
        return false;
    }
}
