package org.example.amazone.Service;

import org.example.amazone.Model.Category;
import org.example.amazone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories=new ArrayList<>();
    public ArrayList<Category> getCategory(){
        return categories;
    }
    public boolean addCategory(Category category){
        for (Category c:categories){
            if (c.getId().equals(category.getId())){
                return false;
            }         }
        categories.add(category);
        return true;
    }

    public boolean updateCategory(int id, Category category){

        for (Category c:categories){
            if (c.getId().equals(id)){
                categories.set(categories.indexOf(c),category);
                return true;
            }
        }
        return false;
    }
    public boolean deleteCategory(int id){
        for (Category c:categories){
            if (c.getId().equals(id)){
                categories.remove(c);
                return true;
            }
        }
        return false;
    }
}
