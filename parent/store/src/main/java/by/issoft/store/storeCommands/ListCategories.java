package by.issoft.store.storeCommands;

import by.issoft.store.Commands;
import by.issoft.store.Store;

public class ListCategories extends Store implements Commands {

    @Override
    public  void execute() {
        System.out.println("== All categories ==");
        getCategoryList().stream()
                .map(category-> String.format("Category --> %s", category.getName()))
                .forEach(System.out::println);
    }

//    public  List<Category> getCategories(){
//        return getRandomStorePopulator().getAllCategories();
//    }

    @Override
    public String toString() {
        return "List categories";
       // return "List categories";
    }
}
