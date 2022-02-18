package by.issoft.store;

import by.issoft.domain.Category.Category;

import java.util.List;

public class Store {




    private List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    //Для доступа к объектам категорий
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
        for(Category category: randomStorePopulator.getAllCategories()){
            System.out.println("Category --> " + category.getName());
        }
    }

    //Заполнение всех категорий магазина продуктами
    public void setAllProducts(){
        for(Category category:getCategoryList()){
            category.addProducts(randomStorePopulator.getProductsForCategory(category));
        }

    }



}

