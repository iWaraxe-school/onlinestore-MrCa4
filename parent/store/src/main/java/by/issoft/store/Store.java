package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.RandomStorePopulator;

import java.util.List;

public class Store {

    private List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    /**
     *Для доступа к объектам категорий
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    public RandomStorePopulator getRandomStorePopulator() {
        return randomStorePopulator;
    }

    public void printAllCategories(){
        System.out.println("============= All categories =============");
        randomStorePopulator.getAllCategories().stream()
                .map(Category::getName)
                .map(i-> String.format("Category --> %s", i))
                .forEach(System.out::println);
    }

    /**
     * Заполнение всех категорий магазина продуктами
     */
    public void setAllProducts(){
       getCategoryList().forEach(category->category.addProducts(randomStorePopulator.getProductsForCategory(category)));
    }



}

