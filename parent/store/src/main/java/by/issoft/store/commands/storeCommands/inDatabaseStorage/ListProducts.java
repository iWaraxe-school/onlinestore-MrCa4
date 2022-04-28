package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.utils.commandUtils.CommandsInterface;


public class ListProducts implements CommandsInterface {


    @Override
    public void execute() {

//            getCategoryList()
//                    .forEach(
//                            category-> category.getProductList(category)
//                                    .forEach(System.out::println)
//                    );

    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Products";
    }
}
