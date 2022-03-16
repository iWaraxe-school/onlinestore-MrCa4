package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.Commands;

import static by.issoft.store.Store.getCategoryList;

;

public class ListCategories implements Commands {

    @Override
    public  void execute() {
        System.out.println("== All categories ==");
        getCategoryList().stream()
                .map(category-> String.format("Category --> %s", category.getName()))
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "List Category";
       // return "List categories";
    }
}
