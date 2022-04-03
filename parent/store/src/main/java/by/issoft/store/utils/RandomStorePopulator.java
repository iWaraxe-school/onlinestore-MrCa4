package by.issoft.store.utils;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.issoft.store.utils.ReflectionGetSubTypes.getAllSubTypes;

public class RandomStorePopulator {

    public static Faker faker = new Faker();
    public static Faker getFaker() {
        return faker;
    }



    public List<Category> getAllCategories(){
        return (List<Category>)getAllSubTypes(Category.class,"by.issoft.domain.Category");
    }

    //???????? range must start from 1
    public List<Product> getProductsForCategory(Category category){
        return new ArrayList<>(generateProductsForCategory(category, Optional.of(new Random().nextInt(10))));
    }

    public List<Product> generateProductsForCategory(Category category, Optional<Integer> countOfProducts){
        countOfProducts = (countOfProducts == null)? Optional.ofNullable(Integer.valueOf(0)) : countOfProducts;
        return IntStream.range(0,countOfProducts.orElse(10))
                .mapToObj(i->new Product(generateProductNameForCurrentCategory(category),generatePrice(),generateRate()))
                .collect(Collectors.toList());

    }

    private String generateProductNameForCurrentCategory(Category category){
        return category.getUsableProductName(faker);
    }

    private Float generatePrice(){
        return (float) faker.number().randomDouble(1,1,1000);
    }

    private Float generateRate(){
        return (float) faker.number().randomDouble(1,1,1000);
    }


}
