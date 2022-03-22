package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.Commands;

public class ListTop5 implements Commands {
    @Override
    public void execute() {
        try {
            new Sort()
                    .makeProductSortingList()
                    .stream()
                    .limit(5)
                    .forEach(p-> System.out.println(
                "Product name: " + p.getName() +
                        "Price: "  + p.getPrice() +
                        "Rate: " + p.getRate()));
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Top5";
    }
}
