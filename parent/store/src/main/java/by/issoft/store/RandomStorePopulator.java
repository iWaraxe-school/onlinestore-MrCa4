package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomStorePopulator {

    private static Faker faker = new Faker();

    public static Faker getFaker() {
        return faker;
    }


    //Получаем список категорий
    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<Category>();
        Reflections reflections = new Reflections("by.issoft.domain.Category", Scanners.SubTypes);
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

    //Получаем готовые продукты
    public List<Product> getProductsForCategory(Category category){
        List<Product> resultList = new ArrayList<>();
        resultList.addAll(generateProductsForCategory(category, new Random().nextInt(10)));
        return resultList;
    }

    //Генерация объектов продуктов для категории
    public List<Product> generateProductsForCategory(Category category, int countOfProducts){

        //Не обязательно,но проверяем
        countOfProducts =  (countOfProducts == 0) ? 10 : countOfProducts;
        List<Product> productsList = new ArrayList<Product>();
        for(int i = 0; i< countOfProducts;i++){
            productsList.add(new Product(generateProductNameForCurrentCategory(category),generatePrice(),generateRate()));
        }
        return productsList;
    }

    //Генерация полей продукта
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
