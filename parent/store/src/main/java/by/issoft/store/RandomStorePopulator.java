package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomStorePopulator {

    private static Faker faker = new Faker();
    public static Faker getFaker() {
        return faker;
    }

    /**
     * Получаем список категорий
     */
    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        Reflections reflections = new Reflections("by.issoft.domain.Category", Scanners.SubTypes);
        Set<Class<? extends Category>> subTypes =
                reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType: subTypes){
            try {
                categoryList.add(subType.getConstructor().newInstance());
            } catch (InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException
                    | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return categoryList;

    }

    /**
     * Получаем готовые продукты
     *
     */
    public List<Product> getProductsForCategory(Category category){
        /*return resultList.stream()
               .map(i->generateProductsForCategory(category, Optional.of(new Random().nextInt(10))))
               .collect(Collectors.toList()).get(0);
         */
        return new ArrayList<>(generateProductsForCategory(category, Optional.of(new Random().nextInt(10))));
    }

    /**
     * Генерация объектов продуктов для категории
     */
    public List<Product> generateProductsForCategory(Category category, Optional<Integer> countOfProducts){

        return IntStream.range(0,countOfProducts.orElse(10))
                .mapToObj(i->new Product(generateProductNameForCurrentCategory(category),generatePrice(),generateRate()))
                .collect(Collectors.toList());

    }

    /**
     * Генерация полей продукта
     */
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
