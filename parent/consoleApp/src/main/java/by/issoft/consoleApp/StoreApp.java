package by.issoft.consoleApp;


import by.issoft.domain.Category.Category;
import by.issoft.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        //Попытка инициализации  и заполнения магазина

        Store store = new Store();
        store.setCategoryList(store.getRandomStorePopulator().getAllCategories());
        store.printAllCategories();
        store.setAllProducts();

        /*На данном этапе магазин проинециализирован и можно уже
          реализовывать функционал */

        for (Category category:store.getCategoryList()){
            category.printAllProduct();
        }

    }
}
