package by.issoft.domain.Category;

import com.github.javafaker.Faker;

import java.util.*;

abstract public class Category {

    private  String name;
    public static Map<String,List<Product>> productsDict = new HashMap<>();
    public Category (String name){
        this.name = name;
    }
    public String getName() { return name; }
    public void addProducts(String categoryName, List<Product> products) {
        productsDict.put(categoryName,products);
    }
    public List<Product> getProductList(Category category){
        return productsDict.get(category.getName());
    }
    public abstract String getUsableProductName(Faker faker);
}
