package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.CommandsInterface;

import static by.issoft.store.Store.getCategoryList;


public class ListProducts implements CommandsInterface {


    @Override
    public void execute() {

            getCategoryList()
                    .forEach(
                            category-> category.getProductList(category).forEach(System.out::println)
                    );


    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Products";
    }
}
