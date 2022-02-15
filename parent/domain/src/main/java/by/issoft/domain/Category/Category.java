package by.issoft.domain.Category;

import com.github.javafaker.Faker;

import java.util.List;

abstract public class Category {

    private  String name;
    private  List<Product> productList;

    public Category (String name){
        this.name = name;
    }

    protected Category() {
    }

    public abstract String getUsableProductName(Faker faker);
}
