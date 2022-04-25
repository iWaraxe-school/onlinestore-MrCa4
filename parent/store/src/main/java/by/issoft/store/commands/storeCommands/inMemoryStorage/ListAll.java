package by.issoft.store.commands.storeCommands.inMemoryStorage;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import lombok.SneakyThrows;

import static by.issoft.store.Store.getCategoryList;

public class ListAll implements CommandsInterface {


    @Override
    @SneakyThrows
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
