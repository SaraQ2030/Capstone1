package org.example.amazone.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.amazone.Model.Category;
import org.example.amazone.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    @Getter
    ArrayList<Product> products = new ArrayList<>();

    public boolean addProduct(Product product) {
        for (Product p:products){
            if (p.getId().equals(product.getId())){
                return false;
            }
        }
        products.add(product);
        return true;
    }


    public boolean updateProduct(int id, Product product) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.set(products.indexOf(p), product);
                return true;
            }
        }
        return false;
    }


    public boolean deleteProduct(int id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }
//filter price on range defined
    public ArrayList<Product> filterPrice(int minPrice, int maxPrice) {
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                list.add(p);
            }
        }
        list.sort(Comparator.comparingDouble(Product::getPrice));
        return list;
    }
}