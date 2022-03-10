package by.issoft.store.storeCommands;

import by.issoft.store.Commands;
import by.issoft.store.Store;

public class ListTop5 extends Store implements Commands {
    @Override
    public void execute() {
        Sort sortCommand = new Sort();
        sortCommand.makeProductSortingList().stream().limit(5).forEach(p-> System.out.println(
                "Product name: " + p.getName() +
                        "Price: "  + p.getPrice() +
                        "Rate: " + p.getRate()));
    }

    @Override
    public String toString() {
        return "Top5";
    }
}
