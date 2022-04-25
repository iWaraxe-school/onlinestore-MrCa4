package by.issoft.store.commands.storeCommands.inMemoryStorage;

import by.issoft.store.utils.commandUtils.CommandsInterface;

import static by.issoft.store.Store.getCategoryList;


public class ListProducts implements CommandsInterface {


    @Override
    public void execute() {

            getCategoryList()
                    .forEach(
                            category-> category.getProductList(category)
                                    .forEach(System.out::println)
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
