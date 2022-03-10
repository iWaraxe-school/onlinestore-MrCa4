package by.issoft.store.storeCommands;

import by.issoft.store.Commands;
import by.issoft.store.Store;


public class ListProducts extends Store implements Commands {


    @Override
    public void execute() {

            getCategoryList()
                    .stream()
                    .forEach(
                            category->category.getProductList(category)
                                    .stream()
                                    .forEach(System.out::println)
                    );


    }

    @Override
    public String toString() {
        return "List products";
    }
}
