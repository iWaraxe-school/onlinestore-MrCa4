package by.issoft.domain.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Category {

    private static String categoryName;
    private static List<Product> productList;

    public Category (String name){
        this.categoryName = name;
    }
    public Category(){};

    public static String getName() {
        return categoryName;
    }

    public static void setName(String name) {
        Category.categoryName = name;
    }

    //Work with products
    public Boolean addProducts(Product product){
       return this.addProducts(new  ArrayList(Arrays.asList(product)));
    }
    public Boolean addProducts(List<Product> products){
        if (productList == null){
        productList = new ArrayList<Product>();}
        productList.addAll(products);
        return true;
    }



}
