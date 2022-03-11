package by.issoft.store.storeCommands;

import by.issoft.store.Commands;
import by.issoft.store.Store;

public class ListCommands extends Store implements Commands {

    @Override
    public void execute() {
        commandDict.keySet().stream().sorted().forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
