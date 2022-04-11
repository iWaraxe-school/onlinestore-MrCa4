package by.issoft.store.storeCommands;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.commandUtils.CommandsInterface;

import static by.issoft.store.Store.getCategoryList;

public class ListAll implements CommandsInterface {


    @Override
    public void execute() {

        for (Category category: getCategoryList()){
            System.out.println(String.format("Category --> %s", category.getName()));
            category.getProductList(category).forEach(System.out::println);
        }
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List All";
    }
}
