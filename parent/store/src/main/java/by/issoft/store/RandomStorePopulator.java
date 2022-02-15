package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator {

    private static Faker faker = new Faker();

    public static Faker getFaker() {
        return faker;
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<Category>();
        Reflections reflections = new Reflections("by.issoft.domain.Category", new SubTypesScanner());
        Set<Class<? extends Category>> subTypes =
                reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType: subTypes){
            try {
                categoryList.add(subType.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return categoryList;

    }

    private List<Product> generateProductsForCategory(Category category, int countOfProducts){

        List<Product> productsList = new ArrayList<Product>();
        for(int i = 0; i<countOfProducts;i++){
            productsList.add(new Product(generateProductNameForCurrentCategory(category),generatePrice(),generateRate()));
        }
        return productsList;
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
