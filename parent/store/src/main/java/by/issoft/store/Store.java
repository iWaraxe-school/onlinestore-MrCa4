package by.issoft.store;

import by.issoft.domain.Category.Category;

public class Store {

    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    public void printAllCategories(){
        System.out.println("============= All categories =============");
        for(Category category: randomStorePopulator.getAllCategories()){
            System.out.println(category.getClass().getSimpleName());
        }
    }


}

 class Main{

        public static void main(String[] args) {
            Store store = new Store();
            store.printAllCategories();
        }
        }
