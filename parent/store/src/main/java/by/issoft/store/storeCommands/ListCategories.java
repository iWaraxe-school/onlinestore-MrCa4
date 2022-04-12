package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.CommandsInterface;

import static by.issoft.store.Store.getCategoryList;

;

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
