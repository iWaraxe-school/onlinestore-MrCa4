package by.issoft.domain.Category;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class Category {

    private  String name;
    private  List<Product>  productList;

    public Category (String name){
        this.name = name;
    }

    /**
     * Для получения имени категории при выводе списка всех категорий
     * @return String
     */
    public String getName() { return name; }

    public void addProducts(List<Product> products) {
        final Optional<List<Product>>  opProductList=Optional.empty();
        productList = opProductList.orElseGet(ArrayList::new);
        productList.addAll(products);
    }

    public void printAllProduct() {
        System.out.println("Current category --> " + getName());
        productList.stream()
                .map(Product::toString)
                .forEach(System.out::println);
    }

    public abstract String getUsableProductName(Faker faker);
}
