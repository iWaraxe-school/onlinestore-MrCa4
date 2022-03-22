package by.issoft.store.storeCommands;

import by.issoft.store.Store;
import by.issoft.store.utils.commanUtils.Commands;

public class ListCommands implements Commands {

    @Override
    public void execute() {
        Store.fabricCommands.printCommandList();
    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
