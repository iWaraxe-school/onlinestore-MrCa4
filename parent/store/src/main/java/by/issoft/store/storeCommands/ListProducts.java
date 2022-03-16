package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.Commands;

import static by.issoft.store.Store.getCategoryList;


public class ListProducts implements Commands {


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
