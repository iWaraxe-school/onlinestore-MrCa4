package by.issoft.store.commands.storeCommands.inMemoryStorage;

import by.issoft.store.utils.commandUtils.CommandsInterface;

import static by.issoft.store.Store.getCategoryList;


public class ListCategories implements CommandsInterface {

    @Override
    public  void execute() {
        System.out.println("== All categories ==");
        getCategoryList().stream()
                .map(category-> String.format("Category --> %s", category.getName()))
                .forEach(System.out::println);
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Category";
    }
}
