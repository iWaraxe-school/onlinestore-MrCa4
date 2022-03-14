package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.Commands;
import by.issoft.store.Store;


public class ListProducts extends Store implements Commands {


    @Override
    public void execute() {

            getCategoryList()
                    .forEach(
                            category-> category.getProductList(category).forEach(System.out::println)
                    );


    }

    @Override
    public String toString() {
        return "List Products";
    }
}
