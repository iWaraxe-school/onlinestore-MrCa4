package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.utils.commandUtils.CommandsInterface;

public class ListTop5 extends Sort implements CommandsInterface {
    @Override
    public void execute() {
            new Sort()
                    .makeProductSortingList()
                    .stream()
                    .limit(5)
                    .forEach(p-> System.out.println(
                "Product name: " + p.getName() +
                        " Price: "  + p.getPrice() +
                        " Rate: " + p.getRate()));
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "Top5";
    }
}
